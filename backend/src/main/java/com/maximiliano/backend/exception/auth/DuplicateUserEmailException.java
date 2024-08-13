package com.maximiliano.backend.exception.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DuplicateUserEmailException extends RuntimeException {
    public DuplicateUserEmailException(String s) {
        super(s);
    }
}
