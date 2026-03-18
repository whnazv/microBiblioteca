package com.example.authservice.domain.port.out;

import com.example.authservice.application.dto.TokenResponse;

public interface LoginUserInKeycloakPort {
    TokenResponse login(String username, String password);
}
