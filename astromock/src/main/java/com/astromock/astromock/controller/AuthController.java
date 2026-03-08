package com.astromock.astromock.controller;

import com.astromock.astromock.model.Users;
import com.astromock.astromock.repository.UserRepository;
import com.astromock.astromock.utility.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users u) {

        if (repo.findByEmail(u.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "User already exists"));
        }

        // Encrypt password
        u.setPassword(encoder.encode(u.getPassword()));

        repo.save(u);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Registered successfully",
                        "name", u.getName()
                )
        );
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String password = req.get("password");

        Users user = repo.findByEmail(email).orElse(null);

        if (user == null || !encoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Invalid credentials"));
        }

        String token = jwt.generate(email);

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "name", user.getName(),
                        "email", user.getEmail()
                )
        );
    }

    // ================= GET CURRENT USER =================
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String email = jwt.extractEmail(token);

        Users user = repo.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "User not found"));
        }
        System.out.println(user.getName());
        return ResponseEntity.ok(
                Map.of(
                        "name", user.getName(),
                        "email", user.getEmail(),
                        "phnno", user.getPhnno()
                )
        );
    }
}