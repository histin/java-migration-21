package com.example.migration;

// Tier A 예시 (단순 Record) — knowledge_base/jep_rules/downgrade_A6_record-to-pojo.md 대상.
public record UserRecord(String name, int age) {

    public boolean isAdult() {
        return age >= 18;
    }

    public String greeting() {
        return "Hello, " + name + "!";
    }
}
