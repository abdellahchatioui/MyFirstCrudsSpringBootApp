package com.crud_app.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import com.crud_app.demo.entity.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    private static final String SECRET_KEY =
        "hnmXQILulOjsHy1qVGLdmnmmfhV5PIOtgDWuRxSysytNqvcORnjfLykMY9PuuVtPNkLpVPjMcKLWie7fjxNxUL";

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    /* =======================
       🔐 Signing Key
    ======================= */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /* =======================
       🎟 Token Generation
    ======================= */
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /* =======================
       📦 Claims Extraction
    ======================= */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /* =======================
       📧 Extract Email
    ======================= */
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    /* =======================
       🎭 Extract Role
    ======================= */
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    /* =======================
       ⏰ Expiration Check
    ======================= */
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    /* =======================
       ✅ Token Validation (FILTER)
    ======================= */
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /* =======================
       🔐 Token Ownership Check (OPTIONAL)
    ======================= */
    public boolean isTokenValid(String token, User user) {
        final String email = extractEmail(token);
        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }
}
