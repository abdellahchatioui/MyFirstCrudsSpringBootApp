package com.crud_app.demo.service;

import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud_app.demo.User;
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
        System.out.println("User Pass : " + newUser.getPassword());
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