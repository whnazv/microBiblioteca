package com.example.authservice.infrastructure.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
