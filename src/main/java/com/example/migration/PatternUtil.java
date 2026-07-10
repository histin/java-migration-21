package com.example.migration;

import java.util.List;

// Tier A 예시 (instanceof Pattern Matching, && 가드 포함) —
// knowledge_base/jep_rules/downgrade_A4_instanceof-pattern-to-cast.md 대상.
public class PatternUtil {

    public String describe(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof String s) {
            return "string:" + s.length();
        } else if (obj instanceof Integer i && i > 0) {
            return "positive-int:" + i;
        } else if (obj instanceof Integer i) {
            return "non-positive-int:" + i;
        } else if (obj instanceof List<?> list) {
            return "list:" + list.size();
        } else {
            return "unknown:" + obj.getClass().getSimpleName();
        }
    }
}
