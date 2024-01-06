package com.devlach.classroom.courses.dto;

import com.devlach.classroom.api.exception.BadRequestException;

public record CreateUpdateCourseDTO(
        Long id,
        String title,
        String description,
        String videoUrl,
        CreateUpdateCoursePricing pricing,
        Integer categoryId
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

    public void validateCreateDraft() {
        if (title == null) {
            throw BadRequestException.requiredField("title");
        }
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
        return title != null && !title.trim().isBlank();
    }

    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public boolean hasVideoUrl() { return videoUrl != null && !videoUrl.trim().isBlank(); }

    public boolean hasCategoryId() {
        return categoryId != null;
    }
}
