package com.maximiliano.backend.exception.project;

public class NoProjectsFoundException extends RuntimeException {
    public NoProjectsFoundException(String s) {
        super(s);
    }
}
