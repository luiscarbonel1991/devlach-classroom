package com.devlach.classroom.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {

    public static final String AVAILABILITY_TIME_FORMAT = "HH:mm";
    private static final String AVAILABILITY_DATE_FORMAT = "yyyy-MM-dd";

    private static final DateTimeFormatter AVAILABILITY_TIME_FORMATTER = DateTimeFormatter.ofPattern(AVAILABILITY_TIME_FORMAT);

    public static LocalTime parseAvailabilityTime(String time) {
        return LocalTime.parse(time, AVAILABILITY_TIME_FORMATTER);
    }

    public static LocalDate parseAvailabilityDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(AVAILABILITY_DATE_FORMAT));
    }

    public static RangeDate<LocalDate> parseRangeAvailabilityDate(String startDate, String endDate) {
        var start = parseAvailabilityDate(startDate);
        var end = parseAvailabilityDate(endDate);
        var range = new RangeDate<>(start, end);
        if(range.getStart().isAfter(range.getEnd())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        return range;
    }
}
