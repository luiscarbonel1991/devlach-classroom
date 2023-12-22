package com.devlach.classroom.users.dto;

import java.util.List;

public record UserDTO(
        String email,
        List<ProfileDTO> profiles
) {
}
