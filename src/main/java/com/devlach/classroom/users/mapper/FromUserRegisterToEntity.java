package com.devlach.classroom.users.mapper;

import com.devlach.classroom.entity.User;

@FunctionalInterface
public interface FromUserRegisterToEntity {

    User toEntity();
}
