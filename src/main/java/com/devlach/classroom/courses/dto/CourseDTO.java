package com.devlach.classroom.courses.dto;

import java.util.List;

public record CourseDTO(
        Long id,
        String title,
        String description,
        List<CoursePricingDTO> pricing
) {
}
