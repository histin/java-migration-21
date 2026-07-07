package com.example.migration;

import java.util.Objects;

// 단순 POJO(필드+생성자+getter+equals/hashCode/toString) → upgrade_05_pojo-to-record.md 대상.
// 일반적인 Java Bean 관례인 getXxx() 네이밍을 사용 — 규칙의 "주의사항"이 정확히 이 케이스를 다룬다:
// Record로 바꾸면 접근자가 getName()이 아니라 name()이 되므로 호출부(테스트 포함)도 함께 바뀐다.
public final class ProductDto {

    private final String name;
    private final double price;

    public ProductDto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDto that = (ProductDto) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "ProductDto{name='" + name + "', price=" + price + "}";
    }
}
