package com.devlach.classroom.schedule.dto;

public record RegularAvailabilityDTO(
        Long id,
        String dayOfWeek,

        int dayOfWeekAsNumber,
        String startTime,
        String endTime
) {
}
