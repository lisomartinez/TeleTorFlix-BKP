package com.teletorflix.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
public class Schedule {

    private final List<DayOfWeek> days;
    private final LocalTime time;

    private Schedule(List<DayOfWeek> days, LocalTime time){
        this.days = days;
        this.time = time;
    }

    public static Schedule of(List<DayOfWeek> days, LocalTime time) {
        return new Schedule(days, time);
    }

}
