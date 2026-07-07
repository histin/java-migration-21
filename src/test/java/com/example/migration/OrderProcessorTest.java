package com.example.migration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();

    private List<OrderProcessor.Order> sampleOrders() {
        List<OrderProcessor.Order> orders = new ArrayList<OrderProcessor.Order>();
        orders.add(new OrderProcessor.Order("A", 10.0, true));
        orders.add(new OrderProcessor.Order("B", 30.0, true));
        orders.add(new OrderProcessor.Order("C", 20.0, false));
        return orders;
    }

    @Test
    void sortByAmountDescending_orders_by_amount() {
        List<OrderProcessor.Order> sorted = processor.sortByAmountDescending(sampleOrders());
        assertEquals("B", sorted.get(0).getCustomerName());
        assertEquals("C", sorted.get(1).getCustomerName());
        assertEquals("A", sorted.get(2).getCustomerName());
    }

    @Test
    void activeCustomerNames_filters_and_maps() {
        List<String> names = processor.activeCustomerNames(sampleOrders());
        assertEquals(2, names.size());
        assertEquals("A", names.get(0));
        assertEquals("B", names.get(1));
    }

    @Test
    void totalAmount_sums_all() {
        assertEquals(60.0, processor.totalAmount(sampleOrders()));
    }

    @Test
    void countActive_counts_only_active() {
        assertEquals(2, processor.countActive(sampleOrders()));
    }

    @Test
    void maxAmount_finds_largest() {
        assertEquals(30.0, processor.maxAmount(sampleOrders()));
    }

    @Test
    void totalActiveAmount_sums_only_active() {
        assertEquals(40.0, processor.totalActiveAmount(sampleOrders()));
    }

    @Test
    void describeProcessingTask_runs_and_returns_marker() {
        assertEquals("ran:job1", processor.describeProcessingTask("job1"));
    }

    @Test
    void handleWithCallback_invokes_handler() {
        assertEquals("handled:x", processor.handleWithCallback("x"));
    }

    // Date/Calendar(Tier B) 검증 — 이 테스트 메서드는 sc002_upgrade_after에서 반환 타입이
    // Date → LocalDate로 바뀌므로(upgrade_09 규칙: 단순 치환이 아니라 모델 자체가 바뀜) 코드가 다르다.
    // 둘 다 "month1Based=6(6월)으로 호출하면 실제로 6월 28일을 가리켜야 한다"는 같은 의미를 검증한다.
    @Test
    void shippingDeadline_month_is_1_based_from_caller_perspective() {
        Date d = processor.shippingDeadline(2026, 6, 28);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        assertEquals(2026, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH)); // Calendar.MONTH는 0-based(JUNE=5)
        assertEquals(28, cal.get(Calendar.DAY_OF_MONTH));
    }
}
