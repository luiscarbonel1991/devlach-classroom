package com.devlach.classroom.schedule.dto;

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
        validateStartTime();
        validateEndTime();
    }

    public void validateUpdate() {
        if (id == null) {
            throw new IllegalArgumentException("id must be present");
        }
        var date = validateDate();
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("date must be in the future");
        }
        var startTime = validateStartTime();
        var endTime = validateEndTime();
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }
    }


    private LocalDate validateDate() {
        return DateUtils.parseAvailabilityDate(date);
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
