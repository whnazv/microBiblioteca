package com.example.authservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellidos;
    private String zona;
    private String departamento;
}
