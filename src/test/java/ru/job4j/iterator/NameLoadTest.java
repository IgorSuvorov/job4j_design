package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void parse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void validateSymbol() {
        String name = "Mike";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name);
    }

    @Test
    void validateKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key");
    }

    @Test
    void validateValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "33=";
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value");
    }
}