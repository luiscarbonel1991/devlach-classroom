package com.devlach.classroom.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponseDTO(
        List<String> errors,
        Integer status,
        String timestamp,
        String code
) {
}
