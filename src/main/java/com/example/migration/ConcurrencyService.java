package com.example.migration;

// Tier C 예시 (Virtual Thread, Java 8 대응 기능 없음) —
// knowledge_base/jep_rules/downgrade_C1_virtual-thread-to-executor.md 대상.
// 다운그레이드 방향에서는 변환을 시도하지 않고 Human Review로 직행해야 한다
// (sc001_downgrade_after/HUMAN_REVIEW.md 참고).
public class ConcurrencyService {

    public String runOnVirtualThread(String taskName) throws InterruptedException {
        var result = new StringBuilder();
        Thread vt = Thread.ofVirtual().start(() -> result.append("done:").append(taskName));
        vt.join();
        return result.toString();
    }
}
