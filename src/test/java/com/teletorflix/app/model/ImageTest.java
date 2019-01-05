package com.teletorflix.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageTest {

    static Stream<Arguments> getArgumentsWithNull() {
        return Stream.of(
                Arguments.of(null, "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg"),
                Arguments.of("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", null)
        );
    }

    @Test
    @DisplayName("Static factory method should return Image Instance")
    void staticFactoryMethod_validFields_ReturnsInstance() {
        Image image = Image.of("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg",
                "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");
        assertThat(image.getMedium()).isEqualTo("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg");
        assertThat(image.getOriginal()).isEqualTo("http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");
    }

    @ParameterizedTest
    @MethodSource("getArgumentsWithNull")
    @DisplayName("Static factory method with null fields should throw NullPointerException")
    void staticFactoryMethod_NullFields_ThrowsNullPointerException(String medium, String original) {
        assertThrows(NullPointerException.class, () -> Image.of(medium, original));
    }
}