package com.maximiliano.backend.exception;

import com.maximiliano.backend.exception.auth.DuplicateUserEmailException;
import com.maximiliano.backend.exception.auth.DuplicateUserUsernameException;
import com.maximiliano.backend.exception.auth.InvalidCredentialsException;
import com.maximiliano.backend.exception.auth.UserNotFoundException;
import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.employee.NoEmployeesFoundException;
import com.maximiliano.backend.exception.payroll.NoPayrollsFoundException;
import com.maximiliano.backend.exception.payroll.PayrollNotFoundException;
import com.maximiliano.backend.exception.project.NoProjectsFoundException;
import com.maximiliano.backend.exception.project.ProjectNotFoundException;
import com.maximiliano.backend.exception.task.NoTasksFoundException;
import com.maximiliano.backend.exception.task.TaskNotFoundException;
import com.maximiliano.backend.exception.times.NoTimeRecordsFoundException;
import com.maximiliano.backend.exception.times.TimeRecordNotFoundException;
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

    // AUTHENTICATION

    @ExceptionHandler(DuplicateUserEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUserEmailException(DuplicateUserEmailException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(DuplicateUserUsernameException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUserUsernameException(DuplicateUserUsernameException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // PAYROLL

    @ExceptionHandler(NoPayrollsFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoPayrollsFoundException(NoPayrollsFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PayrollNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePayrollNotFoundException(PayrollNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // TIME RECORD

    @ExceptionHandler(NoTimeRecordsFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoTimeRecordsFoundException(NoTimeRecordsFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TimeRecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTimeRecordNotFoundException(TimeRecordNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
