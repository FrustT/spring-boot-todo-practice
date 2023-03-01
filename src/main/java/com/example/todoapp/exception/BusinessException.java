package com.example.todoapp.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BusinessException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }

    public String getErrorCode() {
        return httpStatus.name();
    }
}
