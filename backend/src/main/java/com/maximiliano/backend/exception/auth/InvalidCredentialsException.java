package com.maximiliano.backend.exception.auth;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String s) {
        super(s);
    }
}
