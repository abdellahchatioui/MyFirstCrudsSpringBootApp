package com.crud_app.demo.dto;

import com.crud_app.demo.model.Role;

public class UserResponseDTO {

    
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Role role;


    public UserResponseDTO() {}

    public UserResponseDTO(String name, String email,String password,Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
