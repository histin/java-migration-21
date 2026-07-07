package com.example.migration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Raw 타입(제네릭 미지정) → upgrade_08_raw-type-to-diamond-operator.md 대상.
// Arrays.asList()+Collections.unmodifiableList() → upgrade_04_aslist-to-collection-factory.md 대상.
@SuppressWarnings("unchecked")
public class InventoryUtil {

    // Raw 타입 사용 — 제네릭 타입 인자가 전혀 없다(다이아몬드 연산자 도입 이전 스타일).
    public List buildRawList(Object a, Object b, Object c) {
        List result = new ArrayList();
        result.add(a);
        result.add(b);
        result.add(c);
        return result;
    }

    // Arrays.asList() + Collections.unmodifiableList() 패턴 — Java 9+의 List.of()로 단축 가능.
    public List<String> defaultCategories() {
        return Collections.unmodifiableList(Arrays.asList("ELECTRONICS", "GROCERY", "APPAREL"));
    }
}
