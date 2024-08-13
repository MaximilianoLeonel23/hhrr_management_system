package com.maximiliano.backend.exception.task;

public class NoTasksFoundException extends RuntimeException {
    public NoTasksFoundException(String s) {
        super(s);
    }
}
