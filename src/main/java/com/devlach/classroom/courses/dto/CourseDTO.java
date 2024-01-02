package com.devlach.classroom.courses.dto;

import java.util.List;

public record CourseDTO(
        Long id,
        String title,
        String description,
        boolean published,
        String imageUrl,
        String videoUrl,
        List<CoursePricingDTO> pricing
) {
}
