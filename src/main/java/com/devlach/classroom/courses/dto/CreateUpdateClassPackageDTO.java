package com.devlach.classroom.courses.dto;

import com.devlach.classroom.api.exception.BadRequestException;
import com.devlach.classroom.entity.ClassPackageStatusType;

public record CreateUpdateClassPackageDTO(
        Long id,
        String title,
        int numberOfClasses,
        int durationInMinutes,
        boolean isTrial,
        Long coursePricingId,
        String status
) {
    public void validateCreate() {
        requireFieldsToBePresent();
        if(!ClassPackageStatusType.of(status).equals(ClassPackageStatusType.PENDING)) {
            throw BadRequestException.invalidField("status", "Status must be PENDING for new class packages");
        }
    }

    private void requireFieldsToBePresent() {
        if (title == null) {
            throw BadRequestException.invalidField("title", "Title cannot be blank");
        }
        if (numberOfClasses <= 0) {
            throw BadRequestException.invalidField("numberOfClasses", "Number of classes must be greater than 0");
        }
        if (durationInMinutes <= 0) {
            throw BadRequestException.invalidField("durationInMinutes", "Duration in minutes must be greater than 0");
        }
        if (coursePricingId == null) {
            throw BadRequestException.invalidField("coursePricingId", "Course pricing id cannot be blank");
        }
    }
}
