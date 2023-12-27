package com.devlach.classroom.courses.controller;

import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import com.devlach.classroom.courses.gateway.CourseGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseGateway courseGateway;

    public CourseController(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll(@RequestParam String ownerEmail) {
        return ResponseEntity.ok(courseGateway.findAll(ownerEmail));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> findCourseById(@PathVariable Long courseId, @RequestParam String ownerEmail) {
        return ResponseEntity.ok(courseGateway.findById(courseId, ownerEmail));
    }
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CreateUpdateCourseDTO courseDTO, @RequestParam String ownerEmail) {
        return ResponseEntity.ok(courseGateway.create(courseDTO, ownerEmail));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId, @RequestBody CreateUpdateCourseDTO courseDTO, @RequestParam String ownerEmail) {
        return ResponseEntity.ok(courseGateway.update(courseId, courseDTO, ownerEmail));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId, @RequestParam String ownerEmail) {
        courseGateway.delete(courseId, ownerEmail);
        return ResponseEntity.ok().build();
    }
}
