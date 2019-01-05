package com.teletorflix.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExternalsTest {

    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(0, 264492, "tt1553656"),
                Arguments.of(25988, 0, "tt1553656"),
                Arguments.of(0, 0, "tt1553656")
        );
    }

    @Test
    @DisplayName("Static Factory method return instance")
    void staticFactoryMethod_ReturnsExternalsInstance() {
        Externals externals = Externals.of(25988, 264492, "tt1553656");

        assertThat(externals.getTvrage()).isEqualTo(25988);
        assertThat(externals.getThetvdb()).isEqualTo(264492);
        assertThat(externals.getImdb()).isEqualTo("tt1553656");
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    @DisplayName("Static factory method called with illegal arguments throws IllegalArgumentException")
    void staticFactoryMethod_IllegalArgument_ThrowsIllegalArgumentExecption(int tvrage, int thetvdb, String imdb) {
        assertThrows(IllegalArgumentException.class, () -> Externals.of(tvrage, thetvdb, imdb));
    }

    @Test
    @DisplayName("Static factory method called with null imdb field throws NullPointerException")
    void staticFactoryMethod_withNull_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Externals.of(25988, 264492, null));
    }
}