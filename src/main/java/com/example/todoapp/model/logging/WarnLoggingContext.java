package com.example.todoapp.model.logging;

import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
public class WarnLoggingContext {
    private String message;
    private ZonedDateTime timestamp;

    public WarnLoggingContext() {
        this.timestamp = ZonedDateTime.now();
    }

    public WarnLoggingContext(String message) {
        this();
        this.message = message;
    }

    public static WarnLoggingContext getContext(String message) {
        return new WarnLoggingContext(message);
    }

    @Override
    public String toString() {
        return "Warn(timestamp= " + this.getTimestamp() + "message=" + this.getMessage();
    }
}
