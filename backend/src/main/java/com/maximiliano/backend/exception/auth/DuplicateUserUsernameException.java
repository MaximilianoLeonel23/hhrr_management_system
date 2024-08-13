package com.maximiliano.backend.exception.auth;


public class DuplicateUserUsernameException extends RuntimeException {
    public DuplicateUserUsernameException(String s) {
        super(s);
    }
}
