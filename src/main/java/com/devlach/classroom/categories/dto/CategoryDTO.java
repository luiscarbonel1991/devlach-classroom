package com.devlach.classroom.categories.dto;

import java.util.List;

public record CategoryDTO(
        Integer id,
        String name,
        String description,
        CategoryDTO parent,
        List<CategoryDTO> children
) {

}
