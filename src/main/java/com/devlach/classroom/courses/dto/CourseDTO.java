package com.devlach.classroom.courses.dto;

import java.math.BigDecimal;
import java.util.List;

public record  CourseDTO(
        Long id,
        String title,
        String description,
        boolean published,
        Long attachmentImageId,
        String videoUrl,
        BigDecimal pricePerHour,
        BigDecimal priceTrial,
        List<CoursePricingDTO> pricing,
        CourseCategoryDTO category
) {
}
