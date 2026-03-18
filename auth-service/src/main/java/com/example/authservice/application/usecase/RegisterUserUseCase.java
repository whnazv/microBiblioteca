package com.example.authservice.application.usecase;

import com.example.authservice.application.dto.RegisterResponse;
import com.example.authservice.domain.model.User;
import com.example.authservice.domain.model.UserRegistration;
import com.example.authservice.domain.port.out.CreateUserInKeycloakPort;
import com.example.authservice.domain.port.out.SaveUserProfilePort;
import com.example.authservice.domain.port.out.UserEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final CreateUserInKeycloakPort keycloakPort;
    private final SaveUserProfilePort profilePort;
    private final UserEventPublisherPort eventPublisher;

    public RegisterResponse register(UserRegistration registration) {

        String keycloakId = keycloakPort.createUser(registration);

//        profilePort.saveProfile(registration);

        eventPublisher.publishUserRegistered(
                new User(
                        keycloakId,
                        registration.getUsername(),
                        registration.getEmail()
                )
        );

        return new RegisterResponse("Usuario creado en Keycloak, guardado y publicado en Kafka");
    }
}
