package com.maximiliano.backend.exception.times;

public class NoTimeRecordsFoundException extends RuntimeException {
    public NoTimeRecordsFoundException(String s) {
        super(s);
    }
}
