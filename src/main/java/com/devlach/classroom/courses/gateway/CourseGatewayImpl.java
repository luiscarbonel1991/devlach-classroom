package com.devlach.classroom.courses.gateway;

import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import com.devlach.classroom.courses.mapper.CourseMapper;
import com.devlach.classroom.courses.mapper.ToCourseDTO;
import com.devlach.classroom.courses.service.CourseService;
import com.devlach.classroom.entity.Course;
import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.gateway.UserGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseGatewayImpl implements CourseGateway {

    private final UserGateway userGateway;
    private final CourseService courseService;

    public CourseGatewayImpl(UserGateway userGateway, CourseService courseService) {
        this.userGateway = userGateway;
        this.courseService = courseService;
    }

    @Override
    public List<CourseDTO> findAll(String ownerEmail) {
        ProfileDTO profile = findProfileByTeacherEmail(ownerEmail);
        return courseService.findAll(profile).stream()
                .map(CourseMapper::map)
                .map(ToCourseDTO::toDTO)
                .toList();
    }

    @Override
    public CourseDTO create(CreateUpdateCourseDTO createUpdateCourseDTO, String ownerEmail) {
        ProfileDTO profile = findProfileByTeacherEmail(ownerEmail);
        Course course = courseService.create(createUpdateCourseDTO, profile);
        return CourseMapper.map(course).toDTO();
    }

    @Override
    public CourseDTO findById(Long courseId, String ownerEmail) {
        ProfileDTO profile = findProfileByTeacherEmail(ownerEmail);
        return CourseMapper.map(courseService.findByCourseIdAndTeacherId(courseId, profile.id())).toDTO();
    }

    @Override
    public CourseDTO update(Long courseId, CreateUpdateCourseDTO courseDTO, String ownerEmail) {
        var profile = findProfileByTeacherEmail(ownerEmail);
        return CourseMapper.map(courseService.update(courseId, courseDTO, profile)).toDTO();
    }

    @Override
    public void delete(Long courseId, String ownerEmail) {
        var profile = findProfileByTeacherEmail(ownerEmail);
        courseService.delete(courseId, profile.id());
    }

    private ProfileDTO findProfileByTeacherEmail(String ownerEmail) {
        return userGateway.findProfileByEmail(ownerEmail, ProfileType.TEACHER);
    }
}
