package com.crud_app.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud_app.demo.User;
import com.crud_app.demo.repository.UserRepository;
import com.crud_app.demo.service.UserService;

import jakarta.validation.Valid;


@RestController()
@RequestMapping("/api/users")
public class UserController{

    private final UserService userService;
    
    public UserController (UserService userService) {
        this.userService = userService;
    }
 
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserbyId(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.addUser(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User updatedUser, @PathVariable Integer id) {
        updatedUser.setId(null); 
        return ResponseEntity.ok(userService.updateUser(updatedUser, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}