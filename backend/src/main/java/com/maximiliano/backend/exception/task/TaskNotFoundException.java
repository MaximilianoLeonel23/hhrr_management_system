package com.maximiliano.backend.exception.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String s) {
        super(s);
    }
}
