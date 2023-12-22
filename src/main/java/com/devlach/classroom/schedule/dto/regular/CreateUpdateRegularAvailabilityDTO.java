package com.devlach.classroom.schedule.dto.regular;

import com.devlach.classroom.schedule.enums.DayOfWeek;
import com.devlach.classroom.utils.DateUtils;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public record CreateUpdateRegularAvailabilityDTO(
        Long id,
        String dayOfWeek,
        String startTime,
        String endTime
) {

    public CreateUpdateRegularAvailabilityDTO(String dayOfWeek,
                                              String startTime,
                                              String endTime) {
        this(null, dayOfWeek, startTime, endTime);
    }

    public void validateCreate() {
        DayOfWeek.of(dayOfWeek);
        LocalTime start = validateStartTime();
        LocalTime end = validateEndTime();
        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }
    }

    public void validateUpdate() {
        if (id == null) {
            throw new IllegalArgumentException("id must be present");
        }
        DayOfWeek.of(dayOfWeek);
        LocalTime start = validateStartTime();
        LocalTime end = validateEndTime();
        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.of(dayOfWeek);
    }

    public LocalTime getStartTime() {
        return validateStartTime();
    }

    public LocalTime getEndTime() {
        return validateEndTime();
    }

    private LocalTime validateStartTime() {
        try {
            return DateUtils.parseAvailabilityTime(startTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid endTime format. Expected: " + DateUtils.AVAILABILITY_TIME_FORMAT);
        }
    }

    private LocalTime validateEndTime() {
        try {
            return DateUtils.parseAvailabilityTime(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid endTime format. Expected: " + DateUtils.AVAILABILITY_TIME_FORMAT);
        }
    }
}
