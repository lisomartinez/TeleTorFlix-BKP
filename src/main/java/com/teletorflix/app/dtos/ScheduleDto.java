package com.teletorflix.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"time", "days"})
public class ScheduleDto {

    private List<DayOfWeek> days;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    public static ScheduleDto of(List<DayOfWeek> days, LocalTime time) {
        return new ScheduleDto(days, time);
    }

    @JsonGetter(value = "days")
    private List<String> getDaysJson() {
        return days.stream()
                .map(DayOfWeek::toString)
                .map(day -> day.substring(0, 1) + day.substring(1).toLowerCase())
                .collect(Collectors.toList());
    }

    @JsonSetter(value = "days")
    private void setDaysJson(List<String> daysStr) {
        days = daysStr.stream()
                .map(String::toUpperCase)
                .map(DayOfWeek::valueOf)
                .collect(Collectors.toList());
    }
}
