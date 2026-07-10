package com.example.migration;

// Tier B 예시 (Sealed Interface + Record Pattern 구조 분해) —
// knowledge_base/jep_rules/downgrade_B1_sealed-class-to-abstract.md,
// downgrade_B2_record-pattern-to-accessor.md 대상.
public class ShapeRouter {

    public sealed interface Shape permits Circle, Square {}
    public record Circle(double radius) implements Shape {}
    public record Square(double side) implements Shape {}

    public double area(Shape shape) {
        return switch (shape) {
            case Circle(double radius) -> Math.PI * radius * radius;
            case Square(double side) -> side * side;
        };
    }
}
