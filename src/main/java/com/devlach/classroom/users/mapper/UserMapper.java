package com.devlach.classroom.users.mapper;

import com.devlach.classroom.entity.User;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.dto.UserDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static ToUserDTO map(User user) {
        return () -> new UserDTO(
                user.getEmail(),
                user.getProfiles().stream().map(p -> new ProfileDTO(
                        p.getId(),
                        p.getFullName(),
                        p.getType()
                )).toList(
        ));
    }
}
