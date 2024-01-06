package com.devlach.classroom.api.exception;

import com.devlach.classroom.entity.FileExtensionType;
import com.devlach.classroom.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class BadRequestException extends AppException {

    public static final String ERROR_MS_FORMAT = "%s. %s";

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        super.errors.add(message);
    }

    public static BadRequestException invalidWeeklyQuantityDays(int quantityDays) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_QUANTITY_DAYS;
        String message = String.format("%s. Quantity days must be between 1 and 7. Quantity days: %s", errorCode.getMessageCode(), quantityDays);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidWeeklyStartTimeFormat(String startTime) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_START_TIME;
        String message = String.format("%s. Invalid startTime format. Expected: %s. Received: %s", errorCode.getMessageCode(), DateUtils.AVAILABILITY_TIME_FORMAT, startTime);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidWeeklyEndTimeFormat(String startTime) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_END_TIME;
        String message = String.format("%s. Invalid endTime format. Expected: %s. Received: %s", errorCode.getMessageCode(), DateUtils.AVAILABILITY_TIME_FORMAT, startTime);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException requiredField(String fieldName) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_REQUIRED_FIELD;
        String message = String.format("%s. Field name: %s", errorCode.getMessageCode(), fieldName);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException startTimeMustBeBeforeEndTime(String startTime, String endTime) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_STAR_TIME_MUST_BE_BEFORE_END_TIME;
        String message = String.format("%s. Start time: %s, End time: %s", errorCode.getMessageCode(), startTime, endTime);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException dateMustBeInTheFuture(String date) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_DATE_MUST_BE_IN_THE_FUTURE;
        String message = String.format("%s. Date: %s", errorCode.getMessageCode(), date);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidClassPackageDuration(String duration) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_CLASS_PACKAGE_DURATION;
        String message = String.format("%s. Duration: %s", errorCode.getMessageCode(), duration);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidDayOfWeek(String dayOfWeek) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_DAY_OF_WEEK;
        String message = String.format("%s. Day of week: %s", errorCode.getMessageCode(), dayOfWeek);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidClassPackageStatus(String status) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_CLASS_PACKAGE_STATUS;
        String message = String.format("%s. Status: %s", errorCode.getMessageCode(), status);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException pricePerHourMustBeGreaterThanZero(double pricePerHour) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_PRICE_PER_HOUR_MUST_BE_GREATER_THAN_ZERO;
        String message = String.format("%s. Price per hour: %s", errorCode.getMessageCode(), pricePerHour);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException priceTrialMustBeGreaterThanZero(double priceTrial) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_PRICE_TRIAL_MUST_BE_GREATER_THAN_ZERO;
        String message = String.format("%s. Price trial: %s", errorCode.getMessageCode(), priceTrial);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException pricePerHourMustBeGreaterThanPriceTrial(double pricePerHour, double priceTrial) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_PRICE_PER_HOUR_MUST_BE_GREATER_THAN_PRICE_TRIAL;
        String message = String.format("%s. Price per hour: %s, Price trial: %s", errorCode.getMessageCode(), pricePerHour, priceTrial);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidField(String fieldName, String content) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_FIELD;
        String message = String.format("%s. Field name: %s, Content: %s", errorCode.getMessageCode(), fieldName, content);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidWeeklyDate(String date) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_WEEKLY_DATE;
        String message = String.format("%s. Invalid date format. Expected: %s. Received: %s", errorCode.getMessageCode(), DateUtils.AVAILABILITY_DATE_FORMAT, date);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidRequest(HttpMessageConversionException e) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_REQUEST;
        String message = String.format(ERROR_MS_FORMAT, errorCode.getMessageCode(), e.getMessage());
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidRequest(MissingServletRequestParameterException e) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_REQUEST;
        String message = String.format(ERROR_MS_FORMAT, errorCode.getMessageCode(), e.getMessage());
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidRequest(MethodArgumentConversionNotSupportedException e) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_REQUEST;
        String message = String.format(ERROR_MS_FORMAT, errorCode.getMessageCode(), e.getMessage());
        return new BadRequestException(errorCode, message);
    }



    public static BadRequestException uploadFile(Throwable e) {
        log.error("Error occurred while uploading file", e);
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_UPLOAD_FILE;
        String message = String.format("%s.", errorCode.getMessageCode());
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException downloadFile(Throwable e) {
        log.error("Error occurred while downloading file", e);
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_DOWNLOAD_FILE;
        String message = String.format("%s.", errorCode.getMessageCode());
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException deleteFile(Throwable e) {
        log.error("Error occurred while downloading file", e);
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_DELETE_FILE;
        String message = String.format("%s.", errorCode.getMessageCode());
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidImageBySize(long fileSize, long maxImageSize) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_IMAGE_BY_SIZE;
        String message = String.format("%s. File size: %s, Max image size: %s", errorCode.getMessageCode(), fileSize, maxImageSize);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidImageByEmptyOrNull() {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_IMAGE_BY_SIZE;
        String message = String.format("%s. File is empty or null", errorCode.getMessageCode());
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidImageByExtension(String extension, List<FileExtensionType> imagedTypes) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_FILE_EXTENSION_TYPE;
        String message = String.format("%s. Extension: %s, Expected: %s", errorCode.getMessageCode(), extension, imagedTypes);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException invalidEntityType(String entityType) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_INVALID_ENTITY_TYPE;
        String message = String.format("%s. Entity type: %s", errorCode.getMessageCode(), entityType);
        return new BadRequestException(errorCode, message);
    }

    public static BadRequestException categoryCanNotBeDeleted(Integer id) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST_DEFAULT_CATEGORY_CAN_NOT_BE_DELETED;
        String message = String.format(errorCode.getMessageCode(), id);
        return new BadRequestException(errorCode, message);
    }
}


