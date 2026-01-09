package com.crud_app.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud_app.demo.User;

@RestController()
@RequestMapping("/api/users")
public class UserController{

    List<User> users = new ArrayList<>(List.of(
        new User(1, "Abdellah", "Abdellah@gamil.com"),
        new User(2, "Mohamed", "Mohamed@gmail.com")
    ));

    private User findUserById(int id) {
    return users.stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .orElse(null);
}
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOenUser(@PathVariable int id){
        User user = findUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);

    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        int newId = users.size() + 1;
        newUser.setId(newId);
        users.add(newUser);
            return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(newUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser,@PathVariable int id){
        User user = findUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        User user = findUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        users.remove(user);
       return ResponseEntity.ok("User deleted successfully");
    }

}