package com.example.migration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcurrencyServiceTest {

    @Test
    void runOnVirtualThread_returns_done_marker() throws InterruptedException {
        ConcurrencyService svc = new ConcurrencyService();
        assertEquals("done:job1", svc.runOnVirtualThread("job1"));
    }
}
