package com.crud_app.demo.service;


import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud_app.demo.config.JwtService;
import com.crud_app.demo.dto.UserRequestDTO;
import com.crud_app.demo.entity.User;
import com.crud_app.demo.exception.UserNotFoundException;
import com.crud_app.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                    PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /* 
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
     */
    // User Cruds
    public Page<User> getAllUsers(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page,size,sort);

        return userRepository.findAll(pageable);
    }

    public User getUserbyId(int id){
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not Found"));

    }

    public User addUser(UserRequestDTO dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already exists!"); 
        }else if(userRepository.existsByName(dto.getName())){
            throw new IllegalArgumentException("Name already exists!"); 
        }
        User user  = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
       
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser, int id){
        User user = getUserbyId(id);
        if(!user.getEmail().equals(updatedUser.getEmail())
            && userRepository.existsByEmail(updatedUser.getEmail()) ){
            throw new IllegalArgumentException("Email already exists!");
        }else if(!user.getName().equals(updatedUser.getName())
            && userRepository.existsByName(updatedUser.getName())){
            throw new IllegalArgumentException("Name already exists!"); 
        }
        
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}