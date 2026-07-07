package com.example.migration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

// Java 8 스타일 리포트 유틸 — 업그레이드 패턴 검증용 신규 파일 (2026-07-07 모니터 트리거 테스트)
public class ReportUtil {

    // raw 타입 + 명시적 타입 선언 → upgrade_01/08 대상
    public List buildTags(Object a, Object b) {
        List tags = new ArrayList();
        tags.add(a);
        tags.add(b);
        return tags;
    }

    // enhanced-for 누적 → upgrade_07 대상
    public int totalLength(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            if (!line.isEmpty()) {
                total += line.length();
            }
        }
        return total;
    }

    // 익명 Comparator → upgrade_06 대상
    public List<String> sortByLength(List<String> lines) {
        List<String> copy = new ArrayList<String>(lines);
        Collections.sort(copy, new Comparator<String>() {
            @Override
            public int compare(String x, String y) {
                return Integer.compare(x.length(), y.length());
            }
        });
        return copy;
    }

    // Arrays.asList + unmodifiable → upgrade_04 대상
    public List<String> defaultSections() {
        return Collections.unmodifiableList(Arrays.asList("HEADER", "BODY", "FOOTER"));
    }

    // Calendar 0-based month → upgrade_09 대상 (Tier B)
    public Date reportDate(int year, int month1Based, int day) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month1Based - 1, day);
        return cal.getTime();
    }
}
