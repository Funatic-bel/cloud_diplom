package com.example.cloud;

import com.example.cloud.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    private final AuthService authService;

    AuthServiceTest(AuthService authService) {
        this.authService = authService;
    }

    @Test
    void tokenShouldBeInvalid() {
        assertFalse(authService.isValidToken("fake-token"));
    }
}
