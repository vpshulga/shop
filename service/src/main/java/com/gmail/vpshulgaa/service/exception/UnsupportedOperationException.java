package com.gmail.vpshulgaa.service.exception;

public class UnsupportedOperationException extends RuntimeException {

    private String message;

    public UnsupportedOperationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
