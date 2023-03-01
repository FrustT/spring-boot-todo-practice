package com.example.todoapp.controller;

import com.example.todoapp.exception.BusinessException;

import org.springframework.http.HttpStatus;
import com.example.todoapp.model.responses.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorModel> handleBusinessException(BusinessException e) {
        System.out.println("BusinessException: " + e.getMessage());

        return new ResponseEntity<>(
                new ErrorModel(
                        HttpStatus.valueOf(e.getStatusCode()),
                        e.getMessage()),
                HttpStatus.valueOf(e.getStatusCode()));

    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModel> handleException(Exception e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>(
                new ErrorModel(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getMessage(),
                        e.getStackTrace().toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}