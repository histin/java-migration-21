package com.example.migration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

// 익명 클래스 3개(Comparator/Runnable/커스텀 콜백) + for 루프 5개 + Date/Calendar —
// knowledge_base/jep_rules/upgrade_06_anonymous-class-to-lambda.md,
// upgrade_07_for-loop-to-stream.md, upgrade_09_date-calendar-to-java-time.md 대상.
public class OrderProcessor {

    public static class Order {
        private final String customerName;
        private final double amount;
        private final boolean active;

        public Order(String customerName, double amount, boolean active) {
            this.customerName = customerName;
            this.amount = amount;
            this.active = active;
        }

        public String getCustomerName() {
            return customerName;
        }

        public double getAmount() {
            return amount;
        }

        public boolean isActive() {
            return active;
        }
    }

    // 익명 클래스 #1 — Comparator(함수형 인터페이스, 메서드 1개) → upgrade_06 대상.
    public List<Order> sortByAmountDescending(List<Order> orders) {
        List<Order> copy = new ArrayList<Order>(orders);
        Collections.sort(copy, new Comparator<Order>() {
            @Override
            public int compare(Order a, Order b) {
                return Double.compare(b.getAmount(), a.getAmount());
            }
        });
        return copy;
    }

    // for-loop #1 — filter + map (활성 주문 이름만 추출) → upgrade_07 대상.
    public List<String> activeCustomerNames(List<Order> orders) {
        List<String> names = new ArrayList<String>();
        for (Order order : orders) {
            if (order.isActive()) {
                names.add(order.getCustomerName());
            }
        }
        return names;
    }

    // for-loop #2 — reduction(합계) → upgrade_07 대상.
    public double totalAmount(List<Order> orders) {
        double total = 0.0;
        for (Order order : orders) {
            total += order.getAmount();
        }
        return total;
    }

    // for-loop #3 — 조건부 count → upgrade_07 대상.
    public int countActive(List<Order> orders) {
        int count = 0;
        for (Order order : orders) {
            if (order.isActive()) {
                count++;
            }
        }
        return count;
    }

    // for-loop #4 — 최대값 탐색 → upgrade_07 대상.
    public double maxAmount(List<Order> orders) {
        double max = Double.MIN_VALUE;
        for (Order order : orders) {
            if (order.getAmount() > max) {
                max = order.getAmount();
            }
        }
        return max;
    }

    // for-loop #5 — filter + reduction 결합 → upgrade_07 대상.
    public double totalActiveAmount(List<Order> orders) {
        double total = 0.0;
        for (Order order : orders) {
            if (order.isActive()) {
                total += order.getAmount();
            }
        }
        return total;
    }

    // 익명 클래스 #2 — Runnable(함수형 인터페이스) → upgrade_06 대상.
    public String describeProcessingTask(final String taskName) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Processing: " + taskName);
            }
        };
        task.run();
        return "ran:" + taskName;
    }

    // 익명 클래스 #3 — 직접 정의한 단일 메서드 콜백 인터페이스 → upgrade_06 대상.
    public interface ResultHandler {
        void onResult(String message);
    }

    public String handleWithCallback(String input) {
        final StringBuilder captured = new StringBuilder();
        ResultHandler handler = new ResultHandler() {
            @Override
            public void onResult(String message) {
                captured.append(message);
            }
        };
        handler.onResult("handled:" + input);
        return captured.toString();
    }

    // Date/Calendar — Calendar.MONTH가 0-based라는 점이 가장 흔한 변환 버그 지점 → upgrade_09 대상(Tier B).
    public Date shippingDeadline(int year, int month1Based, int day) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month1Based - 1, day); // Calendar.MONTH는 0-based(1월=0)이므로 -1 보정
        return cal.getTime();
    }
}
