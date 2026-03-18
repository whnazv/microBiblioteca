package com.example.authservice.application.usecase;

import com.example.authservice.application.dto.TokenResponse;
import com.example.authservice.domain.port.out.LoginUserInKeycloakPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {

    private final LoginUserInKeycloakPort loginPort;

    public TokenResponse login(String username, String password) {
        return loginPort.login(username, password);
    }
}
