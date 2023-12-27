package com.devlach.classroom.schedule.enums;

import com.devlach.classroom.api.exception.BadRequestException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DayOfWeek {
    SUNDAY("Sunday", 0),
    MONDAY("Monday", 1),
    TUESDAY("Tuesday", 2),
    WEDNESDAY("Wednesday", 3),
    THURSDAY("Thursday", 4),
    FRIDAY("Friday", 5),
    SATURDAY("Saturday", 6);

    private final String dayOfWeekName;

    private final int dayOfWeekNumber;

    DayOfWeek(String dayOfWeekName, int dayOfWeekNumber) {
        this.dayOfWeekName = dayOfWeekName;
        this.dayOfWeekNumber = dayOfWeekNumber;
    }

    public static DayOfWeek of(String dayOfWeek) {
        return Stream.of(values()).filter(d -> d.dayOfWeekName.equalsIgnoreCase(dayOfWeek)).findFirst()
                .orElseThrow(() -> BadRequestException.invalidDayOfWeek(dayOfWeek));
    }

    public static String daysOfWeekAsString() {
        return Stream.of(values()).map(d -> d.dayOfWeekName).collect(Collectors.joining(", "));
    }

    public String dayOfWeek() {
        return dayOfWeekName;
    }

    public int dayOfWeekNumber() {
        return dayOfWeekNumber;
    }
}
