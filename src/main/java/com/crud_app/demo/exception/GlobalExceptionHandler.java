package com.crud_app.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handelValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity 
                .status(HttpStatus.BAD_REQUEST)
                .body("Validation failed");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handerUserNotFound(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    
}
