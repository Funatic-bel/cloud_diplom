package com.example.cloud.service;

import com.example.cloud.dto.LoginRequest;
import com.example.cloud.dto.LoginResponse;
import com.example.cloud.entity.Token;
import com.example.cloud.entity.User;
import com.example.cloud.repository.TokenRepository;
import com.example.cloud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public AuthService(UserRepository userRepository,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }


    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = UUID.randomUUID().toString();

        tokenRepository.save(new Token(token, user.getLogin()));

        return new LoginResponse(token);
    }


    public boolean isValidToken(String token) {
        return tokenRepository.existsById(token);
    }

    public String getLoginByToken(String token) {
        return tokenRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"))
                .getLogin();
    }


    public void logout(String token) {
        tokenRepository.deleteById(token);
    }
}