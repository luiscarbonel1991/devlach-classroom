package com.devlach.classroom.entity;

import com.devlach.classroom.api.exception.BadRequestException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ClassPackageStatusType {
    PENDING,
    PAID,
    ACTIVE,
    COMPLETED,
    CANCELED,
    EXPIRED,
    REFUNDED,
    CHARGEBACK;

    public static ClassPackageStatusType of(String status) {
        return Stream.of(ClassPackageStatusType.values())
                .filter(s -> s.name().equals(status))
                .findFirst()
                .orElseThrow(() -> BadRequestException.invalidClassPackageStatus(status));
    }

    public static String statusAsString() {
        return Stream.of(ClassPackageStatusType.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
