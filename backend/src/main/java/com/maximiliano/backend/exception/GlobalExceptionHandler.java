package com.maximiliano.backend.exception;

import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.employee.NoEmployeesFoundException;
import com.maximiliano.backend.exception.project.NoProjectsFoundException;
import com.maximiliano.backend.exception.project.ProjectNotFoundException;
import com.maximiliano.backend.exception.task.NoTasksFoundException;
import com.maximiliano.backend.exception.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // EMPLOYEES

    @ExceptionHandler(NoEmployeesFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoEmployeesFoundException(NoEmployeesFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateEmployeeEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmployeeEmailException(DuplicateEmployeeEmailException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Validation Jakarta
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // PROJECTS

    @ExceptionHandler(NoProjectsFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoProjectsFoundException(NoProjectsFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProjectNotFound(ProjectNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // TASKS

    @ExceptionHandler(NoTasksFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoTasksFoundException(NoTasksFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFound(TaskNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
