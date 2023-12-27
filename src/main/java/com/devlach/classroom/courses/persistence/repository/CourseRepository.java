package com.devlach.classroom.courses.persistence.repository;

import com.devlach.classroom.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByTeacherProfileIdAndDeletedAtIsNull(Long profileId);
    Course findByIdAndTeacherProfileIdAndDeletedAtIsNull(Long courseId, Long profileId);
}
