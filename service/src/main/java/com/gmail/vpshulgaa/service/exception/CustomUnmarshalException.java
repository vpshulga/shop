package com.gmail.vpshulgaa.service.exception;

public class CustomUnmarshalException extends RuntimeException {

    private String message;

    public CustomUnmarshalException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
