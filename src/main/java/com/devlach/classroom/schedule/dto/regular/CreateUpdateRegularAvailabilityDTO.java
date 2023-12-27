package com.devlach.classroom.schedule.dto.regular;

import com.devlach.classroom.api.exception.BadRequestException;
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
            throw BadRequestException.startTimeMustBeBeforeEndTime(startTime, endTime);
        }
    }

    public void validateUpdate() {
        if (id == null) {
            throw BadRequestException.requiredField("id");
        }
        DayOfWeek.of(dayOfWeek);
        LocalTime start = validateStartTime();
        LocalTime end = validateEndTime();
        if (start.isAfter(end) || start.equals(end)) {
            throw BadRequestException.startTimeMustBeBeforeEndTime(startTime, endTime);
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
            if (startTime == null) {
                throw BadRequestException.requiredField("startTime");
            }
            return DateUtils.parseAvailabilityTime(startTime);
        } catch (DateTimeParseException e) {
            throw BadRequestException.invalidWeeklyStartTimeFormat(startTime);
        }
    }

    private LocalTime validateEndTime() {
        try {
            if (endTime == null) {
                throw BadRequestException.requiredField("endTime");
            }
            return DateUtils.parseAvailabilityTime(endTime);
        } catch (DateTimeParseException e) {
            throw BadRequestException.invalidWeeklyEndTimeFormat(startTime);
        }
    }
}
