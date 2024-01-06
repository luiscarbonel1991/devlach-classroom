package com.devlach.classroom.courses.controller;

import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import com.devlach.classroom.courses.gateway.CourseGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseGateway courseGateway;

    public CourseController(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll(@RequestParam String owner) {
        return ResponseEntity.ok(courseGateway.findAll(owner));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> findCourseById(@PathVariable Long courseId, @RequestParam String owner) {
        return ResponseEntity.ok(courseGateway.findById(courseId, owner));
    }
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CreateUpdateCourseDTO courseDTO,
                                                  @RequestParam String owner,
                                                  UriComponentsBuilder ucb) {
        var courseCreated = courseGateway.createDraft(courseDTO, owner);
        var url = ucb.path("/api/v1/courses/{id}").buildAndExpand(courseCreated.id()).toUri();
        return ResponseEntity.created(url).body(courseCreated);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId, @RequestBody CreateUpdateCourseDTO courseDTO, @RequestParam String owner) {
        return ResponseEntity.ok(courseGateway.update(courseId, courseDTO, owner));
    }

    @PatchMapping("/{courseId}/unpublished")
    public ResponseEntity<CourseDTO> unpublishedCourse(@PathVariable Long courseId, @RequestParam String owner) {
        return ResponseEntity.ok(courseGateway.unpublished(courseId, owner));
    }

    @PatchMapping("/{courseId}/published")
    public ResponseEntity<CourseDTO> publishCourse(@PathVariable Long courseId, @RequestParam String owner) {
        return ResponseEntity.ok(courseGateway.publish(courseId, owner));
    }
    
    @PatchMapping(value = "/{courseId}/upload-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CourseDTO> uploadImage(@PathVariable Long courseId, 
                                                 @RequestParam String owner,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(courseGateway.uploadImage(courseId, owner, name, description, file));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId, @RequestParam String owner) {
        courseGateway.delete(courseId, owner);
        return ResponseEntity.ok().build();
    }
    
    
}
