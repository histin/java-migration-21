package com.example.migration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeRouterTest {

    private final ShapeRouter router = new ShapeRouter();

    @Test
    void area_of_circle() {
        double area = router.area(new ShapeRouter.Circle(2.0));
        assertEquals(Math.PI * 4, area, 0.0001);
    }

    @Test
    void area_of_square() {
        double area = router.area(new ShapeRouter.Square(3.0));
        assertEquals(9.0, area, 0.0001);
    }
}
