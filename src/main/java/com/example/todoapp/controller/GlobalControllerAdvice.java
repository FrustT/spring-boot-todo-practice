package com.example.todoapp.controller;

import jakarta.validation.constraints.NotNull;
import com.example.todoapp.exception.BusinessException;
import org.springframework.http.HttpStatus;
import com.example.todoapp.model.responses.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException e) {
        System.out.println("BusinessException: " + e.getMessage());

        @NotNull
        ErrorModel err = ErrorModel.builder()
                .timestamp(ZonedDateTime.now())
                .statusCode(e.getStatusCode())
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(err, HttpStatus.resolve(e.getStatusCode()));
    }

}