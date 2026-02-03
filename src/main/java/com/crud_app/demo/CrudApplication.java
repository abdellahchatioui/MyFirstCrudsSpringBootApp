package com.crud_app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
        System.out.println("http://localhost:8888/api/users");
        System.out.println("http://localhost:8888/api/auth");
        System.out.println("http://localhost:8888/actuator/mappings");
    }
}


	
