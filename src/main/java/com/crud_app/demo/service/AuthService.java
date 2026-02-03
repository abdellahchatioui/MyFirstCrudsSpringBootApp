package com.crud_app.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud_app.demo.config.JwtService;
import com.crud_app.demo.entity.User;
import com.crud_app.demo.exception.UserNotFoundException;
import com.crud_app.demo.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                    PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    // User Auth
    public String login(User request){
        User existUser = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("Email does not exist"));

        boolean passMatch = passwordEncoder.matches(request.getPassword(), existUser.getPassword());
        if (!passMatch) {
             throw new UserNotFoundException("Invalid password!");  
        }
        
        return jwtService.generateToken(existUser.getEmail());
    }

}