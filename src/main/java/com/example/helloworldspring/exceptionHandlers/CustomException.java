package com.example.helloworldspring.exceptionHandlers;

public class CustomException extends RuntimeException {
    private final ExceptionCodes errorCode;

    public CustomException(ExceptionCodes errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ExceptionCodes getErrorCode() {
        return errorCode;
    }
}
