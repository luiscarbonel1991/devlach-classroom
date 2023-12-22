package com.devlach.classroom.users.controller;

import com.devlach.classroom.users.dto.RegisterUserDTO;
import com.devlach.classroom.users.dto.UserDTO;
import com.devlach.classroom.users.gateway.UserGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserGateway userGateway;

    public UserController(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @GetMapping
    public ResponseEntity<UserDTO> findUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userGateway.findByEmail(email));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        return ResponseEntity.ok(userGateway.registerUser(registerUserDTO));
    }
}
