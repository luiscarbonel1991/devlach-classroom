package com.devlach.classroom.users.mapper;

import com.devlach.classroom.users.dto.UserDTO;

@FunctionalInterface
public interface ToUserDTO {
    UserDTO toUserDTO();
}
