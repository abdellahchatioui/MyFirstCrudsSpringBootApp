package com.crud_app.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    
    @NotBlank(message = "Password is required")
    @Size(min = 3 , message = "size must be great than 3")
    private String password;
    

}
