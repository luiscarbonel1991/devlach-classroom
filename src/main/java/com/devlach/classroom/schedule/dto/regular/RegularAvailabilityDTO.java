package com.devlach.classroom.schedule.dto.regular;

public record RegularAvailabilityDTO(
        Long id,
        String dayOfWeek,

        int dayOfWeekAsNumber,
        String startTime,
        String endTime
) {
}
