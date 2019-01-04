package com.teletorflix.app.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageTest {

    private JacksonTester<Image> json;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Image JSON should return Image instance")
    void deserializeImageJson_ReturnsImage() throws IOException {
        assertThat(json.readObject(JsonFiles.getImageDeserialize())).isEqualTo(getImage());
    }

    private Image getImage() {
        return Image.getInstance("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");
    }

    @Test
    @DisplayName("Serialize Image JSON should return JSON string")
    void serializeImage_ReturnsJsonString() throws IOException {
        assertThat(json.write(getImage())).isEqualToJson(JsonFiles.getImageExpected());
    }

    @Test
    @DisplayName("Serialize null should Throw IllegalArgumentException")
    void serialize_Null_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> json.write(null));
    }

    @Test
    @DisplayName("Deserialize null should Throw IllegalArgumentException")
    void deserialize_Null_Throws() {
        String jsonStr = null;
        assertThrows(IllegalArgumentException.class, () -> json.parse(jsonStr));
    }

    @ParameterizedTest
    @DisplayName("Deserialize Invalid JSON should Trhows MistmatchedInputException")
    @ValueSource(strings = { "{\"medium\": \"http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg\"}",
            "{\"original\": \"http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg\"}"})
    void deserialize_InvalidJson_ThrowsMistmatchedInputException(String jsonStr) {
        assertThrows(MismatchedInputException.class, () -> json.parse(jsonStr));
    }

}