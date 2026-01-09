package com.crud_app.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "It works!";
    }

    @PostMapping("/create")
    public String create(@RequestBody com.crud_app.demo.User user){
        return  user.getName();
    }

    @GetMapping("/search")
    public String search(@RequestParam String name,@RequestParam int age){
        return "Name : " + name + " Age :" + age;
    }

    @PostMapping("/users")
    public ModelUser createUser(@RequestBody ModelUser user){
        return user;
    }
}
