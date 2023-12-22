package com.devlach.classroom.schedule.enums;

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
                .orElseThrow(() -> new IllegalArgumentException("Invalid dayOfWeek: " + dayOfWeek));
    }

    public String dayOfWeek() {
        return dayOfWeekName;
    }

    public int dayOfWeekNumber() {
        return dayOfWeekNumber;
    }
}
