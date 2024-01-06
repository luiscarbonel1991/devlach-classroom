package com.devlach.classroom.users.dto;

import java.util.List;

public record UserDTO(
        Long id,
        String email,
        List<ProfileDTO> profiles
) {

    public UserDTO(String email, List<ProfileDTO> profiles) {
        this(null, email, profiles);
    }
}
