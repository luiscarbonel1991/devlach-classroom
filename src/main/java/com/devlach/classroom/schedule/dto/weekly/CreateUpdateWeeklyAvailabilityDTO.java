package com.devlach.classroom.schedule.dto.weekly;

import com.devlach.classroom.api.exception.BadRequestException;
import com.devlach.classroom.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
        if (date == null) {
            throw BadRequestException.requiredField("date");
        }
        try{
            var parseDate = DateUtils.parseAvailabilityDate(date);
            if (parseDate.isBefore(LocalDate.now())) {
                throw BadRequestException.dateMustBeInTheFuture(date);
            }
        } catch (DateTimeParseException e) {
            throw BadRequestException.invalidWeeklyDate(date);
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
        if (startTime == null) {
            throw BadRequestException.requiredField("startTime");
        }
        try {
            return DateUtils.parseAvailabilityTime(startTime);
        } catch (Exception e) {
            throw BadRequestException.invalidWeeklyStartTimeFormat(startTime);
        }
    }

    private LocalTime validateEndTime() {
        if (endTime == null) {
            throw BadRequestException.requiredField("endTime");
        }
        try {
            return DateUtils.parseAvailabilityTime(endTime);
        } catch (Exception e) {
            throw BadRequestException.invalidWeeklyEndTimeFormat(startTime);
        }
    }
}
