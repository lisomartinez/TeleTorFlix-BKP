package com.teletorflix.app.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teletorflix.app.dtos.ExternalsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExternalsJsonTest {

    private JacksonTester<ExternalsDto> json;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Externals JSON should return valid Externals")
    void ExternalsFromJson_DeserializedExternalsInstance() throws IOException {
        File jsonFile = JsonTestFiles.getExternalsExpected();
        ExternalsDto expected = getExternals();
        ExternalsDto externalsDto = json.readObject(jsonFile);

        assertThat(externalsDto).isEqualTo(expected);
    }

    private ExternalsDto getExternals() {
        return ExternalsDto.of(25988, 264492, "tt1553656");
    }

    @Test
    @DisplayName("Serialize Externals should return valid JSON string")
    void SerializedExternalsInstance_JsonExternals() throws IOException {
        File jsonFile = JsonTestFiles.getExternalsExpected();
        ExternalsDto externalsDto = getExternals();
        JsonContent<ExternalsDto> externalsDtoJson = json.write(externalsDto);
        assertThat(externalsDtoJson).isEqualToJson(jsonFile);
    }

    @Test
    @DisplayName("Serialize a null object should throw IllegalArgumentException")
    void SerializeExternals_null_throwsIllegalArgumentException() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> json.write(null));
    }


    @Test
    @DisplayName("Deserialize a null object should throw IllegalArgumentException")
    void Deserialize_Null_ThrowsIllegalArgumentException() {
        String jsonStr = null;
        assertThrows(IllegalArgumentException.class, () -> json.read(jsonStr));
    }
}