package com.example.todoapp.controller;

import com.example.todoapp.exception.BusinessException;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import com.example.todoapp.model.responses.ErrorModel;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

        @ExceptionHandler(value = BusinessException.class)
        public ResponseEntity<ErrorModel> handleBusinessException(BusinessException e) {

                log.error("BusinessException: " + e.getMessage());

                return new ResponseEntity<>(
                                new ErrorModel(
                                                HttpStatus.valueOf(e.getStatusCode()),
                                                e.getMessage()),
                                HttpStatus.valueOf(e.getStatusCode()));

        }

        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        protected ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

                log.error("MethodArgumentNotValidException: " + e.getMessage());

                return new ResponseEntity<>(
                                new ErrorModel(
                                                HttpStatus.BAD_REQUEST,
                                                e.getMessage()),
                                HttpStatus.BAD_REQUEST);

        }

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<ErrorModel> handleException(Exception e) {
                log.error("Exception: " + e.getMessage());
                return new ResponseEntity<>(
                                new ErrorModel(
                                                HttpStatus.INTERNAL_SERVER_ERROR,
                                                e.getMessage(),
                                                Arrays.toString(e.getStackTrace())),
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }

}