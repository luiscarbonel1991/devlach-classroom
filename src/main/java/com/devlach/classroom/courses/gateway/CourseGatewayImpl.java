package com.devlach.classroom.courses.gateway;

import com.devlach.classroom.attachment.dto.CreateAttachmentDTO;
import com.devlach.classroom.attachment.gateway.AttachmentGateway;
import com.devlach.classroom.categories.gateway.CategoryGateway;
import com.devlach.classroom.courses.dto.CourseDTO;
import com.devlach.classroom.courses.dto.CreateUpdateCourseDTO;
import com.devlach.classroom.courses.mapper.CourseMapper;
import com.devlach.classroom.courses.mapper.ToCourseDTO;
import com.devlach.classroom.courses.service.CourseService;
import com.devlach.classroom.entity.Course;
import com.devlach.classroom.entity.EntityType;
import com.devlach.classroom.entity.ProfileType;
import com.devlach.classroom.users.dto.ProfileDTO;
import com.devlach.classroom.users.gateway.UserGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class CourseGatewayImpl implements CourseGateway {

    private final UserGateway userGateway;

    private final CategoryGateway categoryGateway;

    private final AttachmentGateway attachmentGateway;
    private final CourseService courseService;

    public CourseGatewayImpl(UserGateway userGateway, CategoryGateway categoryGateway, AttachmentGateway attachmentGateway, CourseService courseService) {
        this.userGateway = userGateway;
        this.categoryGateway = categoryGateway;
        this.attachmentGateway = attachmentGateway;
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
        validateCategoryIfExist(createUpdateCourseDTO);
        Course course = courseService.create(createUpdateCourseDTO, profile);
        return CourseMapper.map(course).toDTO();
    }

    @Override
    public CourseDTO createDraft(CreateUpdateCourseDTO createUpdateCourseDTO, String ownerEmail) {
        ProfileDTO profile = findProfileByTeacherEmail(ownerEmail);
        Course course = courseService.createDraft(createUpdateCourseDTO, profile);
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
        validateCategoryIfExist(courseDTO);
        return CourseMapper.map(courseService.update(courseId, courseDTO, profile)).toDTO();
    }

    @Override
    public void delete(Long courseId, String ownerEmail) {
        var profile = findProfileByTeacherEmail(ownerEmail);
        courseService.delete(courseId, profile.id());
    }

    @Override
    public CourseDTO unpublished(Long courseId, String ownerEmail) {
        var profile = findProfileByTeacherEmail(ownerEmail);
        return CourseMapper.map(courseService.unpublished(courseId, profile.id())).toDTO();
    }

    @Override
    public CourseDTO publish(Long courseId, String ownerEmail) {
        var profile = findProfileByTeacherEmail(ownerEmail);
        return CourseMapper.map(courseService.publish(courseId, profile.id())).toDTO();
    }

    @Transactional
    @Override
    public CourseDTO uploadImage(Long courseId, String owner, String name, String description, MultipartFile file) {
        var profile = findProfileByTeacherEmail(owner);
        var dto = new CreateAttachmentDTO(
                file,
                name,
                description,
                EntityType.COURSE
        );
        var imageCreated = attachmentGateway.upload(dto, owner);
        var newAndOldCourse = courseService.uploadImage(courseId, profile.id(), imageCreated.id());
        var oldCourse = newAndOldCourse.get("oldCourse");
        if (oldCourse != null && oldCourse.getImageAttachment() != null) {
            attachmentGateway.delete(oldCourse.getImageAttachment().getId());
        }
        return CourseMapper.map(newAndOldCourse.get("newCourse")).toDTO();
    }

    private ProfileDTO findProfileByTeacherEmail(String ownerEmail) {
        return userGateway.findProfileByEmail(ownerEmail, ProfileType.TEACHER);
    }

    private void validateCategoryIfExist(CreateUpdateCourseDTO courseDTO) {
        if(courseDTO.hasCategoryId()) {
            categoryGateway.findById(courseDTO.categoryId());
        }
    }
}
