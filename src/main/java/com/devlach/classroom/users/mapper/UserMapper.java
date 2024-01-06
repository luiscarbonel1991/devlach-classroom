package com.devlach.classroom.users.mapper;

import com.devlach.classroom.entity.Profile;
import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.entity.User;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.dto.RegisterUserDTO;
import com.devlach.classroom.users.dto.UserDTO;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserMapper {

    public static ToUserDTO map(User user) {
        return () -> new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getProfiles().stream().map(p -> new ProfileDTO(
                        p.getId(),
                        p.getFullName(),
                        p.getType()
                )).toList(
        ));
    }

    public static FromUserRegisterToEntity map(RegisterUserDTO registerUserDTO) {
        // Generate fake Data for Profile
        Profile student = new Profile();
        student.setFullName(registerUserDTO.fullName());
        student.setType(ProfileType.STUDENT);
        student.setBio("I am a student");

        Profile teacher = new Profile();
        teacher.setFullName(registerUserDTO.fullName());
        teacher.setType(ProfileType.TEACHER);
        teacher.setBio("I am a teacher");

        User user = new User();
        user.setUsername(registerUserDTO.email().split("@")[0]);
        user.setEmail(registerUserDTO.email());
        user.setPassword(registerUserDTO.password());
        user.setProfiles(List.of(student, teacher));

        return () -> user;
    }
}
