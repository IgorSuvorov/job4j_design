package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.withPrecision;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(8, 5);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).
                isNotNegative()
                .isPositive()
                .isEven()
                .isEqualTo(8);

    }

    @Test
    void isExist() {
        Box box = new Box(8, 5);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void getArea() {
        Box box = new Box(8, 8);
        double area = box.getArea();
        assertThat(area)
                .isPositive()
                .isGreaterThan(383)
                .isLessThan(385)
                .isCloseTo(384d, withPrecision(0.01d));
    }
}