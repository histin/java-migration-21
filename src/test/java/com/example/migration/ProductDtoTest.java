package com.example.migration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 이 테스트는 sc002_upgrade_after와 호출 규약이 다르다(getName()/getPrice() vs name()/price()) —
// upgrade_05_pojo-to-record.md 규칙의 "주의사항"이 명시하는, Record 변환 시 호출부도 함께 바뀌는 지점이다.
class ProductDtoTest {

    @Test
    void getters_return_constructor_values() {
        ProductDto dto = new ProductDto("Widget", 9.99);
        assertEquals("Widget", dto.getName());
        assertEquals(9.99, dto.getPrice());
    }

    @Test
    void equals_and_hashCode_are_value_based() {
        ProductDto a = new ProductDto("Widget", 9.99);
        ProductDto b = new ProductDto("Widget", 9.99);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
