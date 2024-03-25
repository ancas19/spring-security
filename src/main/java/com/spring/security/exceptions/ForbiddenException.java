package com.spring.security.exceptions;

public class ForbiddenException extends Exception {
    public ForbiddenException(String message) {
        super(message);
    }
}
