package com.teletorflix.app.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teletorflix.app.dtos.ImageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImageJsonTest {

    private JacksonTester<ImageDto> json;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Image JSON should return Image instance")
    void deserializeImageJson_ReturnsImage() throws IOException {
        File jsonFile = JsonTestFiles.getImageDeserialize();
        ImageDto expected = getImage();

        ImageDto imageDto = json.readObject(jsonFile);

        assertThat(imageDto).isEqualTo(expected);
    }

    private ImageDto getImage() {
        return ImageDto.of("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");
    }

    @Test
    @DisplayName("Serialize Image JSON should return JSON string")
    void serializeImage_ReturnsJsonString() throws IOException {
        ImageDto imageDto = getImage();
        File expected = JsonTestFiles.getImageExpected();

        JsonContent<ImageDto> jsonContent = json.write(imageDto);

        assertThat(jsonContent).isEqualToJson(expected);
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

}