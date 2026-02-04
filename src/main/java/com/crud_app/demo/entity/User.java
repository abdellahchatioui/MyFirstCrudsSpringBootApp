package com.crud_app.demo.entity;

import com.crud_app.demo.model.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3 , message = "size must be great than 3")
    private String name;

    
    @NotBlank(message = "Password is required")
    @Size(min = 3 , message = "size must be great than 3")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


}
