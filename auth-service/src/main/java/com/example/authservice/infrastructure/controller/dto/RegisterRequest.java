package com.example.authservice.infrastructure.controller.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String nombre;
    private String apellidos;
    private String zona;
    private String departamento;

}
