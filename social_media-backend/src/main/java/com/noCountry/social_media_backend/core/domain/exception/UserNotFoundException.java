package com.noCountry.social_media_backend.core.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("El usuario no existe.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
