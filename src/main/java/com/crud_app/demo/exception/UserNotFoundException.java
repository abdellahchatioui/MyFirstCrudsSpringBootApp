package com.crud_app.demo.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    } 
    
}
