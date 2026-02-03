package com.crud_app.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud_app.demo.dto.UserRequestDTO;
import com.crud_app.demo.entity.User;
import com.crud_app.demo.service.AuthService;
import com.crud_app.demo.service.UserService;


@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private UserService userService;
    public AuthController(AuthService authService,UserService userService){
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "work !!";
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        String token = authService.login(request); 
        return ResponseEntity.ok(Map.of("token",token));
    }
    

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestDTO request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.addUser(request));
    }
    
}
