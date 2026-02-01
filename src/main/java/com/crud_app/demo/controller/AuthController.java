package com.crud_app.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud_app.demo.dto.LoginRequest;
import com.crud_app.demo.entity.User;
import com.crud_app.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "work !!";
    }

    
    @PostMapping("/singin")
    public ResponseEntity<User> login(@RequestBody User request) {
        User user = userService.login(request);
        return ResponseEntity.ok(user);
    }
    
}
