package com.crud_app.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
    public List<User> getAllUser(){
        return users;
    }

    @GetMapping("/{id}")
    public Object getOenUser(@PathVariable int id){
        User user = findUserById(id);
        if (user == null) {
            return "User Not found";
        }
        return user;

    }

    @PostMapping()
    public String addUser(@RequestBody User newUser){
        int newId = users.size() + 1;
        newUser.setId(newId);
        users.add(newUser);
        return "User Added " + newUser.getName() +" successfully";
 
        
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody User updatedUser,@PathVariable int id){
        User user = findUserById(id);
        if (user == null) {
            return "User Not Found";
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        return "User updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        User user = findUserById(id);
        if (user == null) {
            return "User not found";
        }
        users.remove(user);
       return "User deleted successfully";
    }

}