package com.crud_app.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud_app.demo.entity.User;
import com.crud_app.demo.service.AuthService;
import com.crud_app.demo.service.UserService;


@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping("/test")
    public String test(){
        return "work !!";
    }

    
    @PostMapping("/singin")
    public ResponseEntity<?> login(@RequestBody User request) {
        String token = authService.login(request);
        return ResponseEntity.ok(Map.of("token",token));
    }
    
}
