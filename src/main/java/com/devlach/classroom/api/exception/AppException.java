package com.devlach.classroom.api.exception;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AppException extends RuntimeException{

    private final ErrorCode errorCode;
    protected final Instant timestamp = Instant.now();
    protected List<String> errors = new ArrayList<>();

    public AppException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
