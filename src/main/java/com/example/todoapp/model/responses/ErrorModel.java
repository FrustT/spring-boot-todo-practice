package com.example.todoapp.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorModel {

    private ZonedDateTime timestamp;
    @NotNull
    private int statusCode;
    @NotNull
    private String errorCode;
    private String message;

}
