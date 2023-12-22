package com.devlach.classroom.api;

import com.devlach.classroom.api.dto.ErrorResponseDTO;
import com.devlach.classroom.api.exception.AppException;
import com.devlach.classroom.api.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class ControllerAdviseHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleException(AppException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return getResponseEntityByException(e);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return getResponseEntityByException(ServerException.unexpectedError(e));
    }

    private static ResponseEntity<Object> getResponseEntityByException(AppException e) {
        ResponseStatus responseStatus = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
        HttpStatus status = responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(toErrorResponseDTO(e, status));
    }

    private static ErrorResponseDTO toErrorResponseDTO(AppException e, HttpStatus status) {
        return ErrorResponseDTO.builder()
                .code(e.getErrorCode().name())
                .errors(e.getErrors())
                .status(status.value())
                .timestamp(e.getTimestamp().toString())
                .build();
    }
}