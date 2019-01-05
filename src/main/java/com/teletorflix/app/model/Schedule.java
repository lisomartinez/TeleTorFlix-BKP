package com.teletorflix.app.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"time", "days"})
public class Schedule {

    private final List<DayOfWeek> days;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm")
    private final LocalTime time;

    private Schedule(List<DayOfWeek> days, LocalTime time){
        this.days = Objects.requireNonNull(days, "days cannot be null");
        this.time = Objects.requireNonNull(time, "Time cannot be null");
    }

    @JsonCreator
    public static Schedule from(@JsonProperty(value = "days", required = true) List<String> days,
                                @JsonProperty(value = "time", required = true) LocalTime time) {

        List<DayOfWeek> daysOfWeek = days.stream()
                .map(String::toUpperCase)
                .map(DayOfWeek::valueOf)
                .collect(Collectors.toList());

        return new Schedule(daysOfWeek, time);
    }

    public static Schedule of(List<DayOfWeek> days, LocalTime time) {
        return new Schedule(days, time);
    }

    @JsonGetter(value = "days")
    private List<String> getDaysJson() {
        return days.stream()
                .map(DayOfWeek::toString)
                .map(day -> day.substring(0,1) + day.substring(1).toLowerCase())
                .collect(Collectors.toList());
    }
}
