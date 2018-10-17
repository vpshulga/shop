package com.gmail.vpshulgaa.service.exception;

public class EntityNotFoundException extends RuntimeException {

    private String message;

    public EntityNotFoundException(Class<?> clazz, Long id) {
        this.message = clazz.getSimpleName() + " with id=" + id + " not found";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
