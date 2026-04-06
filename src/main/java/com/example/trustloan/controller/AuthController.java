package com.example.trustloan.controller;

import com.example.trustloan.entity.User;
import com.example.trustloan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User dbUser = userRepository
                .findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return dbUser; // ✅ return full object
    }
}