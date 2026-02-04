package com.crud_app.demo.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.crud_app.demo.entity.User;
import com.crud_app.demo.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 🔑 JWT Authentication Filter
 *
 * This filter intercepts every request and:
 * 1. Extracts the JWT token from Authorization header
 * 2. Validates the token
 * 3. Sets authentication in the SecurityContext
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1️⃣ Get Authorization header
        final String authHeader = request.getHeader("Authorization");

        // 2️⃣ If no header or doesn't start with Bearer → continue without auth
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3️⃣ Extract token and email
        String token = authHeader.substring(7);
        String email = null;

        try {
            email = jwtService.extractEmail(token);
        } catch (Exception e) {
            // Invalid token → continue without authentication
            filterChain.doFilter(request, response);
            return;
        }

        // 4️⃣ Check if user is already authenticated
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 5️⃣ Load user from DB
            User user = userRepository.findByEmail(email).orElse(null);

            // 6️⃣ Validate token & set authentication
            if (user != null && jwtService.isTokenValid(token, user)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user, // principal
                                null, // credentials (already checked via JWT)
                                Collections.emptyList() // authorities, can map roles here later
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 7️⃣ Store authentication in context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 8️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}
