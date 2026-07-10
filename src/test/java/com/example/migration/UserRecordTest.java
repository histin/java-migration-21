package com.example.migration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRecordTest {

    @Test
    void isAdult_true_when_18_or_older() {
        assertTrue(new UserRecord("Yojae", 30).isAdult());
        assertTrue(new UserRecord("Edge", 18).isAdult());
    }

    @Test
    void isAdult_false_when_under_18() {
        assertFalse(new UserRecord("Kid", 17).isAdult());
    }

    @Test
    void greeting_uses_name() {
        assertEquals("Hello, Yojae!", new UserRecord("Yojae", 30).greeting());
    }

    @Test
    void accessors_and_equality_are_record_generated() {
        UserRecord a = new UserRecord("Yojae", 30);
        UserRecord b = new UserRecord("Yojae", 30);
        assertEquals("Yojae", a.name());
        assertEquals(30, a.age());
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
