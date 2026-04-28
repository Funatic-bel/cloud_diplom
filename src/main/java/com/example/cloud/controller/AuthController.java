package com.example.cloud.controller;

import com.example.cloud.dto.LoginRequest;
import com.example.cloud.dto.LoginResponse;
import com.example.cloud.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }


    @PostMapping("/logout")
    public void logout(@RequestHeader("auth-token") String token) {
        authService.logout(token);
    }
}