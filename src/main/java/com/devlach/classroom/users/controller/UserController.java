package com.devlach.classroom.users.controller;

import com.devlach.classroom.users.dto.UserDTO;
import com.devlach.classroom.users.gateway.UserGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
