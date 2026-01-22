package com.crud_app.demo.mapper;

import com.crud_app.demo.User;
import com.crud_app.demo.dto.UserRequestDTO;
import com.crud_app.demo.dto.UserResponseDTO;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

}
