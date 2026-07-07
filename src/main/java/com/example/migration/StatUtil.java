package com.example.migration;

import java.util.ArrayList;
import java.util.List;

// Java 8 스타일 통계 유틸 — 2차 모니터 트리거 테스트 (2026-07-07)
public class StatUtil {

    // 익명 Runnable → upgrade_06 대상
    public String runNamed(final String name) {
        Runnable job = new Runnable() {
            @Override
            public void run() {
                System.out.println("stat:" + name);
            }
        };
        job.run();
        return "ok:" + name;
    }

    // enhanced-for 최대값 탐색 → upgrade_07 대상
    public int maxValue(List<Integer> values) {
        int max = Integer.MIN_VALUE;
        for (Integer v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    // 명시적 타입 + 제네릭 반복 → upgrade_01/08 대상
    public List<Integer> positives(List<Integer> values) {
        List<Integer> out = new ArrayList<Integer>();
        for (Integer v : values) {
            if (v > 0) {
                out.add(v);
            }
        }
        return out;
    }
}
