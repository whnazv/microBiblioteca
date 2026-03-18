package com.example.authservice.infrastructure.controller;

import com.example.authservice.application.dto.RegisterResponse;
import com.example.authservice.application.dto.TokenResponse;
import com.example.authservice.application.usecase.RegisterUserUseCase;
import com.example.authservice.application.usecase.LoginUserUseCase;
import com.example.authservice.domain.model.UserRegistration;
import com.example.authservice.infrastructure.controller.dto.RegisterRequest;
import com.example.authservice.infrastructure.controller.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        UserRegistration registration = new UserRegistration(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getNombre(),
                request.getApellidos(),
                request.getZona(),
                request.getDepartamento()
        );

        return registerUserUseCase.register(registration);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return loginUserUseCase.login(request.getUsername(), request.getPassword());
    }
}
