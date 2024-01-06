package com.devlach.classroom.courses.mapper;

import com.devlach.classroom.courses.dto.CourseCategoryDTO;
import com.devlach.classroom.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseCategoryMapper {

    public static ToCourseCategoryDTO map(Category category) {
        return () -> new CourseCategoryDTO(
                category.getId(),
                category.getName()
        );
    }
}
