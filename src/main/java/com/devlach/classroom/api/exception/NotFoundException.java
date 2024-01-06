package com.devlach.classroom.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends AppException{

    public static final String S_ID_S = "%s. Id: %s";

    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        super.errors.add(message);
    }

    public static NotFoundException weeklyAvailabilityId(Long availabilityId) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_WEEKLY_AVAILABILITY_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), availabilityId);
        return new NotFoundException(errorCode, message);
    }

    public static NotFoundException regularAvailabilityId(Long availabilityId) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_REGULAR_AVAILABILITY_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), availabilityId);
        return new NotFoundException(errorCode, message);
    }

    public static NotFoundException userEmail(String email) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_USER_EMAIL;
        String message = String.format("%s. Email: %s", errorCode.getMessageCode(), email);
        return new NotFoundException(errorCode, message);
    }

    public static NotFoundException userHasNoProfile(String email, String profileType) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_USER_PROFILE;
        String message = String.format("%s. Email: %s, Profile type: %s", errorCode.getMessageCode(), email, profileType);
        return new NotFoundException(errorCode, message);
    }

    public static NotFoundException courseId(Long courseId) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_COURSE_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), courseId);
        return new NotFoundException(errorCode, message);
    }

    public static RuntimeException coursePricingId(Long coursePricingId) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_COURSE_PRICING_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), coursePricingId);
        return new NotFoundException(errorCode, message);
    }

    public static RuntimeException attachmentNotFound(Long id) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_ATTACHMENT_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), id);
        return new NotFoundException(errorCode, message);
    }

    public static RuntimeException parentCategoryId(Integer parentId) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_PARENT_CATEGORY_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), parentId);
        return new NotFoundException(errorCode, message);
    }

    public static RuntimeException categoryId(Integer id) {
        ErrorCode errorCode = ErrorCode.NOT_FOUND_CATEGORY_ID;
        String message = String.format(S_ID_S, errorCode.getMessageCode(), id);
        return new NotFoundException(errorCode, message);
    }
}
