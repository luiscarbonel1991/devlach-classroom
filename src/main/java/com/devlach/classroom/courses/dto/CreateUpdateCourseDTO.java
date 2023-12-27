package com.devlach.classroom.courses.dto;

import com.devlach.classroom.api.exception.BadRequestException;

public record CreateUpdateCourseDTO(
        Long id,
        String title,
        String description,
        CreateUpdateCoursePricing pricing
) {

    public void validateCreate() {
        if (title == null) {
            throw BadRequestException.requiredField("title");
        }
        if (description == null) {
            throw BadRequestException.requiredField("description");
        }
        if (pricing == null) {
            throw BadRequestException.requiredField("pricing");
        }
        pricing.validate();
    }

    public void validateUpdate() {
        if (id == null) {
            throw BadRequestException.requiredField("id");
        }

        if(pricing != null) {
            pricing.validate();
        }
    }

    public void validateUpdateWithoutId() {

        if(hasPricing()) {
            pricing.validate();
        }
    }

    public boolean hasPricing() {
        return pricing != null;
    }

    public boolean hasTitle() {
        return title != null;
    }

    public boolean hasDescription() {
        return description != null;
    }
}
