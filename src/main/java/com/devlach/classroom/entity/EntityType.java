package com.devlach.classroom.entity;

import com.devlach.classroom.api.exception.BadRequestException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EntityType {
    COURSE;

    public static EntityType of(String entityType) {
        return Stream.of(values())
                .filter(type -> type.name().equalsIgnoreCase(entityType))
                .findFirst()
                .orElseThrow(() -> BadRequestException.invalidEntityType(entityType));
    }

    public static String entityTypesAsString() {
        return Stream.of(values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}


