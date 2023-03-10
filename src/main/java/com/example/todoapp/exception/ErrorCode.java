package com.example.todoapp.exception;

public enum ErrorCode {

    UNKNOWN(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    RESOURCE_MISSING(404),
    ACCOUNT_ALREADY_EXISTS(409),
    ACCOUNT_MISSING(404),
    PASSWORD_MISMATCH(409),
    ACCOUNT_ALREADY_VERIFIED(403),
    ACCOUNT_NOT_VERIFIED(403),
    CODE_EXPIRED(410),
    CODE_MISMATCH(409),
    ALREADY_ONBOARDED(409),
    INSUFFICENT_BALANCE(409),
    CONFLICT(409),
    ALREADY_SUBMITTED(409),
    VALIDATION(422);

    private final int httpCode;

    ErrorCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
