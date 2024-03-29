package com.example.helloworldspring.exceptionHandlers;

public enum ExceptionCodes {
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    INVALID_CREDENTIALS("Invalid credentials"),
    INVALID_TOKEN("Invalid token"),
    INVALID_ROLE("Invalid role");

    private final String message;

    ExceptionCodes(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
