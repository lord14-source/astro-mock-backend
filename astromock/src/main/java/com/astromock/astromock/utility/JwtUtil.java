package com.astromock.astromock.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key key;

    // ✅ Initialize key AFTER secret injection
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ✅ Generate JWT
    public String generate(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000) // 24h
                )
                .signWith(key)
                .compact();
    }

    // ✅ Extract username/email
    public String extract(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Validate token
    public boolean isValid(String token, UserDetails user) {
        try {
            String username = extract(token);

            return username.equals(user.getUsername())
                    && !isExpired(token);

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}