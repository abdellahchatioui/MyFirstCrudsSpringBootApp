package com.crud_app.demo.service;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud_app.demo.dto.LoginRequest;
import com.crud_app.demo.entity.User;
import com.crud_app.demo.exception.UserNotFoundException;
import com.crud_app.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /* 
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
     */


    // User Auth
    public User login(User request){
        User existUser = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("Email does not exist"));


        // System.out.println("Password : " + passwordEncoder.encode(request.getPassword()));
        boolean passMatch = passwordEncoder.matches(request.getPassword(), existUser.getPassword());
        if (!passMatch) {
             throw new UserNotFoundException("Invalid password!");  
        }
        return existUser;
    }

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

    public User addUser(User newUser){
        if(userRepository.existsByEmail(newUser.getEmail())){
            throw new IllegalArgumentException("Email already exists!"); 
        }else if(userRepository.existsByName(newUser.getName())){
            throw new IllegalArgumentException("Name already exists!"); 
        }
        newUser.setPassword(
            passwordEncoder.encode(newUser.getPassword())
        );
        System.out.println("333 User Pass : " +passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
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