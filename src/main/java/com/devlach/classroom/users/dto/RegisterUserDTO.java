package com.devlach.classroom.users.dto;

public record RegisterUserDTO(
        String email,
        String password,
        String fullName
) {
}
