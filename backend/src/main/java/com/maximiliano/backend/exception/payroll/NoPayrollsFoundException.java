package com.maximiliano.backend.exception.payroll;

public class NoPayrollsFoundException extends RuntimeException {
    public NoPayrollsFoundException(String s) {
        super(s);
    }
}
