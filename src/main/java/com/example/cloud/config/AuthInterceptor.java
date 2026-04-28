package com.example.cloud.config;

import com.example.cloud.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();


        if (uri.equals("/login")) {
            return true;
        }

        String token = request.getHeader("auth-token");

        if (token == null || !authService.isValidToken(token)) {
            response.setStatus(401);
            return false;
        }

        return true;
    }
}