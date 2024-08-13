package com.maximiliano.backend.exception.employee;

import jakarta.validation.constraints.Email;

public class DuplicateEmployeeEmailException extends RuntimeException {
    public DuplicateEmployeeEmailException(String s) {
        super(s);
    }
}
