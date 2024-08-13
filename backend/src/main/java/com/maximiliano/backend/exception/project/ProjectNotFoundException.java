package com.maximiliano.backend.exception.project;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String s) {
        super(s);
    }
}
