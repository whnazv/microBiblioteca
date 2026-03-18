package com.example.authservice.domain.port.out;

import com.example.authservice.domain.model.UserRegistration;

public interface CreateUserInKeycloakPort {
    String createUser(UserRegistration registration);
}
