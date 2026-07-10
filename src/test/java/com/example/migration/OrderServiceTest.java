package com.example.migration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private final OrderService service = new OrderService();

    @Test
    void calculateShippingFee_weekend_is_5() {
        assertEquals(5.0, service.calculateShippingFee(1));
        assertEquals(5.0, service.calculateShippingFee(7));
    }

    @Test
    void calculateShippingFee_weekday_is_2() {
        assertEquals(2.0, service.calculateShippingFee(3));
    }

    @Test
    void calculateShippingFee_invalid_day_throws() {
        assertThrows(IllegalArgumentException.class, () -> service.calculateShippingFee(8));
    }

    @Test
    void buildReceipt_matches_expected_text_exactly() {
        String receipt = service.buildReceipt("Yojae", 100.0, 5.0);
        // Java8/Java21 양쪽 구현이 byte-for-byte 동일한 문자열을 내야 "동작 동일성"이 입증된다.
        String expected = "Receipt\n"
                + "-------\n"
                + "Customer: Yojae\n"
                + "Amount:   100.00\n"
                + "Fee:      5.00\n"
                + "Total:    105.00\n";
        assertEquals(expected, receipt);
    }
}
