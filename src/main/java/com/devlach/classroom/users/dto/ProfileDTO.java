package com.devlach.classroom.users.dto;

import com.devlach.classroom.entity.ProfileType;

public record ProfileDTO(
        Long id,
        String fullName,
        ProfileType type
) {
}
