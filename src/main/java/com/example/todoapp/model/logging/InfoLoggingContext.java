package com.example.todoapp.model.logging;

import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class InfoLoggingContext {
    private String message;
    private ZonedDateTime timestamp;

    public InfoLoggingContext() {
        this.timestamp = ZonedDateTime.now();
    }

    public InfoLoggingContext(String message) {
        this();
        this.message = message;
    }

    public static InfoLoggingContext getContext(String message) {
        return new InfoLoggingContext(message);
    }

    @Override
    public String toString() {
        return "Info(timestamp=" + this.getTimestamp() + " + this.getMessage() " + this.getMessage() + ")";
    }
}
