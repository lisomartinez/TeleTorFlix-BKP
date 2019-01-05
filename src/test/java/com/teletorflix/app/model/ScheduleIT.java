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
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduleIT {

    private JacksonTester<Schedule> json;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Schedule JSON with ONE day should return Schedule instance")
    void deserialize_ScheduleJSONWithOneDay_ReturnsSchedule() throws IOException {
        Schedule schedule = getScheduleWithOneDay();
        String scheduleJson = getScheduleJsonWithOneDay();

        assertThat(json.parse(scheduleJson)).isEqualTo(schedule);
    }

    private Schedule getScheduleWithOneDay() {
        return Schedule.of(List.of(DayOfWeek.THURSDAY), LocalTime.of(22, 0));
    }

    private String getScheduleJsonWithOneDay() {
        return "{ \"time\": \"22:00\", \"days\": [ \"Thursday\" ] }";
    }

    @Test
    @DisplayName("Deserialize Schedule JSON with TWO days should return Schedule instance")
    void deserialize_ScheduleJSONWithTwoDays_ReturnsSchedule() throws IOException {
        Schedule schedule = getScheduleWithTwoDays();
        String scheduleJson = getScheduleJsonWithTwoDay();

        assertThat(json.parse(scheduleJson)).isEqualTo(schedule);
    }

    private Schedule getScheduleWithTwoDays() {
        return Schedule.of(List.of(DayOfWeek.MONDAY, DayOfWeek.THURSDAY), LocalTime.of(22, 0));
    }

    private String getScheduleJsonWithTwoDay() {
        return "{ \"time\": \"22:00\", \"days\": [ \"Monday\", \"Thursday\" ] }";
    }

    @Test
    @DisplayName("Serialize Schedule with ONE day should return JSON string")
    void serializeSchedule_ScheduleWithOneDay_ReturnJSONString() throws IOException {
        Schedule schedule = getScheduleWithOneDay();
        String jsonExpected = getScheduleJsonWithOneDay();

        assertThat(json.write(schedule)).isEqualToJson(jsonExpected);
    }

    @Test
    @DisplayName("Serialize Schedule with TWO days should return JSON String")
    void serializeSchedule_ScheduleWithTwoDay_ReturnJSONString() throws IOException {
        Schedule schedule = getScheduleWithTwoDays();
        String jsonExpected = getScheduleJsonWithTwoDay();

        assertThat(json.write(schedule)).isEqualToJson(jsonExpected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"{ \"days\": [ \"Monday\", \"Thursday\" ] }", "{ \"time\": \"22:00\" }"})
    @DisplayName("Deserialize Invalid JSON should throw MismatchedInputException")
    void deserializeJsonSchedule_InvalidJson_Throws(String jsonStr) {
        assertThrows(MismatchedInputException.class, () -> json.parse(jsonStr));
    }
}
