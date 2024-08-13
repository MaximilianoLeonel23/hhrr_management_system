package com.maximiliano.backend.exception.employee;


public class NoEmployeesFoundException extends RuntimeException {
    public NoEmployeesFoundException(String message) {
        super(message);
    }
}
