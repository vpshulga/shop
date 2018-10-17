package com.gmail.vpshulgaa.service.exception;

public class ApiUserException extends RuntimeException {

    private String message;

    public ApiUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
