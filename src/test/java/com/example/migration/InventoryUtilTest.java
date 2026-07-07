package com.example.migration;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InventoryUtilTest {

    private final InventoryUtil util = new InventoryUtil();

    @SuppressWarnings("unchecked")
    @Test
    void buildRawList_collects_in_order() {
        List result = util.buildRawList("a", "b", "c");
        assertEquals(3, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
    }

    @Test
    void defaultCategories_contains_expected_values() {
        List<String> categories = util.defaultCategories();
        assertEquals(3, categories.size());
        assertEquals("ELECTRONICS", categories.get(0));
        assertEquals("GROCERY", categories.get(1));
        assertEquals("APPAREL", categories.get(2));
    }

    @Test
    void defaultCategories_is_unmodifiable() {
        List<String> categories = util.defaultCategories();
        assertThrows(UnsupportedOperationException.class, () -> categories.add("NEW"));
    }
}
