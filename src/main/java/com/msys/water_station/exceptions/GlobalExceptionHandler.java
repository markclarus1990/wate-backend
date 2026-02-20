package com.msys.water_station.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidToken(InvalidTokenException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                java.time.LocalDateTime.now(),
                "Invalid Token",
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFound(CustomerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                java.time.LocalDateTime.now(),
                "Customer Not Found",
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                java.time.LocalDateTime.now(),
                "User Not Found",
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<?> handleUsersNotFound(UsersNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                java.time.LocalDateTime.now(),
                "Users Not Found",
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // Check if the error message contains the specific duplicate entry text
        if (ex.getMessage() != null && ex.getMessage().contains("Duplicate entry")) {
            Map<String, String> error = new HashMap<>();
            error.put("email", "Email already exists in the database.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT); // 409 Conflict is standard for duplicates
        }

        return new ResponseEntity<>("Database error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                java.time.LocalDateTime.now(),
                "Access Denied",
                "You do not have the required permissions to perform this action.");
        // Returns an HTTP 403 Forbidden status
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
