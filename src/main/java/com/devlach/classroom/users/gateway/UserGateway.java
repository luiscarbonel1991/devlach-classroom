package com.devlach.classroom.users.gateway;

import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.dto.UserDTO;

public interface UserGateway {
    UserDTO findByEmail(String email);

    ProfileDTO findProfileByEmail(String email, ProfileType type);
}
