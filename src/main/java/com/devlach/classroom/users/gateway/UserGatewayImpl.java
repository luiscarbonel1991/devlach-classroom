package com.devlach.classroom.users.gateway;

import com.devlach.classroom.api.exception.NotFoundException;
import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.dto.RegisterUserDTO;
import com.devlach.classroom.users.dto.UserDTO;
import com.devlach.classroom.users.mapper.UserMapper;
import com.devlach.classroom.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserGatewayImpl implements UserGateway {

    private final UserService userService;

    public UserGatewayImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userService.findUserByEmail(email)
                .map(user -> UserMapper.map(user).toUserDTO())
                .orElseThrow(() -> NotFoundException.userEmail(email));
    }

    @Override
    public ProfileDTO findProfileByEmail(String email, ProfileType type) {
        return findByEmail(email).profiles()
                .stream()
                .filter(profileDTO -> type.equals(profileDTO.type()))
                .findFirst()
                .orElseThrow(() -> NotFoundException.userHasNoProfile(email, type.name().toLowerCase()));
    }

    @Override
    public UserDTO registerUser(RegisterUserDTO registerUserDTO) {
        return UserMapper.map(userService.registerUser(registerUserDTO)).toUserDTO();
    }


}
