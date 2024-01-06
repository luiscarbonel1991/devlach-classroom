package com.devlach.classroom.categories.dto;

public record CreateUpdateCategoryDTO(
        String name,
        String description,
        Integer parentId
) {
}
