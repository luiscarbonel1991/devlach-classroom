package com.devlach.classroom.entity;

import com.devlach.classroom.api.exception.BadRequestException;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ClassPackageDurationType {
    TRIAL(25),
    HALF_HOUR(25),
    ONE_HOUR(50);

    private final int durationInMinutes;

    ClassPackageDurationType(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public static ClassPackageDurationType of(String duration) {
        return Stream.of(ClassPackageDurationType.values())
                .filter(type -> type.name().equalsIgnoreCase(duration))
                .findFirst()
                .orElseThrow(() -> BadRequestException.invalidClassPackageDuration(duration));
    }

    public static String durationsAsString() {
        return Stream.of(ClassPackageDurationType.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
