package com.devlach.classroom.courses.dto;

public record ClassPackageDTO(
        Long id,
        String title,
        int numberOfClasses,
        int durationInMinutes,
        boolean isTrial
) {

}
