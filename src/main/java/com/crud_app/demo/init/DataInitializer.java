package com.crud_app.demo.init;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.crud_app.demo.entity.User;
import com.crud_app.demo.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            User u1 = new User();
            u1.setName("Sara Benali");
            u1.setEmail("sara@gmail.com");
            u1.setPassword(passwordEncoder.encode("1234"));

            User u2 = new User();
            u2.setName("Youssef Amrani");
            u2.setEmail("youssef@gmail.com");
            u2.setPassword(passwordEncoder.encode("1234"));

            User u3 = new User();
            u3.setName("Omar El Fassi");
            u3.setEmail("omar@gmail.com");
            u3.setPassword(passwordEncoder.encode("1234"));

            userRepository.saveAll(List.of(u1, u2, u3));
        }
    }
}
