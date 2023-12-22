package com.devlach.classroom.schedule.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record WeeklyAvailabilityDTO(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime
) {

    public WeeklyAvailabilityDTO(LocalDate date,
                                 LocalTime startTime,
                                 LocalTime endTime) {
        this(null, date, startTime, endTime);
    }
}
