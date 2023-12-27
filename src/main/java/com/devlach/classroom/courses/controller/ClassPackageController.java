package com.devlach.classroom.courses.controller;

import com.devlach.classroom.courses.dto.ClassPackageDTO;
import com.devlach.classroom.courses.dto.CreateUpdateClassPackageDTO;
import com.devlach.classroom.courses.gateway.ClassPackageGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/packages")
public class ClassPackageController {
    private final ClassPackageGateway classPackageGateway;

    public ClassPackageController(ClassPackageGateway classPackageGateway) {
        this.classPackageGateway = classPackageGateway;
    }

    @PostMapping
    public ResponseEntity<ClassPackageDTO> createPackage(@RequestBody CreateUpdateClassPackageDTO dto,
                                                         @RequestParam String email,
                                                         UriComponentsBuilder ucb) {
        var url = ucb.path("/api/v1/packages/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(url).body(classPackageGateway.create(dto, email));
    }
}
