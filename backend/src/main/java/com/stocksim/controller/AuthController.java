package com.stocksim.controller;

import org.springframework.web.bind.annotation.*;
import com.stocksim.service.UserService;
import com.stocksim.model.User;
import com.stocksim.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService us){ this.userService = us; }

    @PostMapping("/register")
    public User register(@RequestBody LoginRequest req){
        return userService.register(req.getUsername(), req.getPassword());
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest req){
        return userService.login(req.getUsername(), req.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
