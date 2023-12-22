package com.devlach.classroom.schedule.dto.weekly;

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
            throw new IllegalArgumentException("id must be present");
        }
    }


    private void validateDate() {
        var parseDate = DateUtils.parseAvailabilityDate(date);
        if (parseDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("date must be in the future");
        }
    }

    private void validateRangeTime() {
        var startTime = validateStartTime();
        var endTime = validateEndTime();
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }
    }

    private LocalTime validateStartTime() {
        try {
            return DateUtils.parseAvailabilityTime(startTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid startTime format. Expected: HH:mm");
        }
    }

    private LocalTime validateEndTime() {
        try {
            return DateUtils.parseAvailabilityTime(endTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid endTime format. Expected: HH:mm");
        }
    }
}
