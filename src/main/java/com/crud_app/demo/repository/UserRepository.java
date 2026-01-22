package com.crud_app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud_app.demo.User;
public interface UserRepository extends JpaRepository<User,Integer>{
    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
