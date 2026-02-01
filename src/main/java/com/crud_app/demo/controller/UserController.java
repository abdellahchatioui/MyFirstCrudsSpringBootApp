package com.crud_app.demo.controller;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud_app.demo.dto.LoginRequest;
import com.crud_app.demo.entity.User;
import com.crud_app.demo.service.UserService;

import jakarta.validation.Valid;


@RestController()
@RequestMapping("/api/users")
public class UserController{

    private final UserService userService;
    
    public UserController (UserService userService) {
        this.userService = userService;
    }
 
    /* 
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    */
   
    
    @GetMapping("/test")
    public String test(){
        return "work !!";
    }


    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ){
        return ResponseEntity.ok(
            userService.getAllUsers(page, size, sortBy, direction)
        );
    } 

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserbyId(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        // System.out.println("User PassCon : " + user);
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