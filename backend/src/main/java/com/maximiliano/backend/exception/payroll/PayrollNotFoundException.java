package com.maximiliano.backend.exception.payroll;

public class PayrollNotFoundException extends RuntimeException {
    public PayrollNotFoundException(String s) {
        super(s);
    }
}
