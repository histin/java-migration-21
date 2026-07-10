package com.example.migration;

// Tier A 예시 (var, Switch Expression, Text Block) —
// knowledge_base/jep_rules/downgrade_A1_var-to-explicit-type.md,
// downgrade_A3_switch-expression-to-statement.md,
// downgrade_A2_text-block-to-concat.md 대상.
public class OrderService {

    public double calculateShippingFee(int dayOfWeek) {
        var fee = switch (dayOfWeek) {
            case 1, 7 -> 5.0;
            case 2, 3, 4, 5, 6 -> 2.0;
            default -> throw new IllegalArgumentException("Invalid dayOfWeek: " + dayOfWeek);
        };
        return fee;
    }

    public String buildReceipt(String customerName, double amount, double fee) {
        var total = amount + fee;
        // 들여쓰기 모호성을 없애기 위해 내용과 닫는 """ 모두 들여쓰기 0으로 맞춤
        // (공통 들여쓰기 제거 규칙에 영향받지 않게 의도적으로 작성).
        return """
Receipt
-------
Customer: %s
Amount:   %.2f
Fee:      %.2f
Total:    %.2f
""".formatted(customerName, amount, fee, total);
    }
}
