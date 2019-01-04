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

class ExternalsTest {

    private JacksonTester<Externals> json;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Externals JSON should return valid Externals")
    void ExternalsFromJson_DeserializedExternalsInstance() throws IOException {
        assertThat(json.readObject(JsonFiles.getExternalsExpected())).isEqualTo(getExternals());
    }

    private Externals getExternals() {
        return Externals.getInstance(25988, 264492, "tt1553656");
    }

    @Test
    @DisplayName("Serialize Externals should return valid JSON string")
    void SerializedExternalsInstance_JsonExternals() throws IOException {
        assertThat(json.write(getExternals())).isEqualToJson(JsonFiles.getExternalsExpected());
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


    @ParameterizedTest
    @DisplayName("An invalid JSON (missing any Externals field) should throw MistmatchedInputException")
    @ValueSource(strings = {"{ \"tvrage\": 25988, \"thetvdb\": 264492}", "{ \"tvrage\": 25988, \"imdb\": \"tt1553656\"}", "{ \"thetvdb\": 264492, \"imdb\": \"tt1553656\"}"})
    void Deserialized_BadFormatJson_(String jsonExternal) throws IOException {
        assertThrows(MismatchedInputException.class, () -> json.parse(jsonExternal));
    }
}