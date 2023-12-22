package com.devlach.classroom.users.service;

import com.devlach.classroom.api.exception.ConflictException;
import com.devlach.classroom.entity.User;
import com.devlach.classroom.users.dto.RegisterUserDTO;
import com.devlach.classroom.users.mapper.UserMapper;
import com.devlach.classroom.users.persistence.ProfileRepository;
import com.devlach.classroom.users.persistence.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    public UserService(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User registerUser(RegisterUserDTO registerUserDTO) {
        findUserByEmail(registerUserDTO.email())
                .ifPresent(user -> {
                    throw ConflictException.userEmailAlreadyExists(registerUserDTO.email());
                });
        User user = UserMapper.map(registerUserDTO).toEntity();
        var userCreated = userRepository.save(user);
        user.getProfiles().forEach(p -> p.setUser(userCreated));
        var profiles = profileRepository.saveAll(user.getProfiles());
        user.setProfiles(profiles);
        return user;
    }
}
