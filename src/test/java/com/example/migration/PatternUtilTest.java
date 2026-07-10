package com.example.migration;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternUtilTest {

    private final PatternUtil util = new PatternUtil();

    @Test
    void describe_string() {
        assertEquals("string:5", util.describe("hello"));
    }

    @Test
    void describe_positive_int() {
        assertEquals("positive-int:5", util.describe(5));
    }

    @Test
    void describe_non_positive_int() {
        assertEquals("non-positive-int:-3", util.describe(-3));
        assertEquals("non-positive-int:0", util.describe(0));
    }

    @Test
    void describe_list() {
        assertEquals("list:3", util.describe(List.of(1, 2, 3)));
    }

    @Test
    void describe_null() {
        assertEquals("null", util.describe(null));
    }

    @Test
    void describe_unknown_type() {
        assertEquals("unknown:Double", util.describe(3.14));
    }
}
