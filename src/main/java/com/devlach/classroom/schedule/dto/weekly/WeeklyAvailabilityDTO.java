package com.devlach.classroom.schedule.dto.weekly;

import java.time.LocalDate;

public record WeeklyAvailabilityDTO(
        Long id,
        LocalDate date,
        String startTime,
        String endTime
) {

    public WeeklyAvailabilityDTO(LocalDate date,
                                 String startTime,
                                 String endTime) {
        this(null, date, startTime, endTime);
    }
}
