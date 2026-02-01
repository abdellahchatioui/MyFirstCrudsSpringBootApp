package com.crud_app.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // allow POST with JSON

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/**",     // login/register endpoints
                    "/api/users/**",    // CRUD endpoints
                    "/h2-console/**"    // H2 database
                ).permitAll()
                .anyRequest().authenticated()
            )

            // Disable default login page
            .formLogin(form -> form.disable())

            // Allow H2 console frames
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}

    /*
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/error").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())   
            .formLogin(Customizer.withDefaults());

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
         */



