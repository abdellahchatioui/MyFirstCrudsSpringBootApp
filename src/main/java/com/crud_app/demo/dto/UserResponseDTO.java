package com.crud_app.demo.dto;

import com.crud_app.demo.model.Role;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Role role;

}
