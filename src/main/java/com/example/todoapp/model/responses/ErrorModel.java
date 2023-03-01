package com.example.todoapp.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorModel {

    private ZonedDateTime timestamp;
    private int statusCode;
    private String errorCode;
    private String message;
    private String stackTrace;

    public ErrorModel() {
        this.timestamp = ZonedDateTime.now();
    }

    public ErrorModel(HttpStatus status, String message) {
        this();
        this.statusCode = status.value();
        this.errorCode = status.name();
        this.message = message;
    }

    public ErrorModel(HttpStatus status, String message, String stackTrace) {
        this(status, message);
        this.stackTrace = stackTrace;

    }

}
