package com.maximiliano.backend.exception.employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
