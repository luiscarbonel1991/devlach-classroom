package com.devlach.classroom.schedule.dto.weekly;

import com.devlach.classroom.api.exception.BadRequestException;
import com.devlach.classroom.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateUpdateWeeklyAvailabilityDTO(
        Long id,
        String date,
        String startTime,
        String endTime
) {

    public CreateUpdateWeeklyAvailabilityDTO(String date,
                                             String startTime,
                                             String endTime) {
        this(null, date, startTime, endTime);
    }

    public void validateCreate() {
        validateDate();
        validateRangeTime();
    }

    public void validateUpdate() {
        validateId();
        validateDate();
        validateRangeTime();
    }

    private void validateId() {
        if (id == null) {
            throw BadRequestException.requiredField("id");
        }
    }


    private void validateDate() {
        var parseDate = DateUtils.parseAvailabilityDate(date);
        if (parseDate.isBefore(LocalDate.now())) {
            throw BadRequestException.dateMustBeInTheFuture(date);
        }
    }

    private void validateRangeTime() {
        var startTime = validateStartTime();
        var endTime = validateEndTime();
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw BadRequestException.startTimeMustBeBeforeEndTime(startTime.toString(), endTime.toString());
        }
    }

    private LocalTime validateStartTime() {
        try {
            return DateUtils.parseAvailabilityTime(startTime);
        } catch (Exception e) {
            throw BadRequestException.invalidWeeklyStartTimeFormat(startTime);
        }
    }

    private LocalTime validateEndTime() {
        try {
            return DateUtils.parseAvailabilityTime(endTime);
        } catch (Exception e) {
            throw BadRequestException.invalidWeeklyEndTimeFormat(startTime);
        }
    }
}
