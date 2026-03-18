package com.example.authservice.infrastructure.service;

import com.example.authservice.domain.model.UserRegistration;
import com.example.authservice.domain.port.out.SaveUserProfilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserProfileClient implements SaveUserProfilePort {

    private final RestTemplate restTemplate;

    /**
     * Envía el perfil del usuario al microservicio de perfiles.
     */
    @Override
    public void saveProfile(UserRegistration registration) {

        String email = registration.getEmail() != null ? registration.getEmail() : "";
        String nombre = registration.getNombre() != null ? registration.getNombre() : "";
        String apellidos = registration.getApellidos() != null ? registration.getApellidos() : "";
        String zona = registration.getZona() != null ? registration.getZona() : "";
        String departamento = registration.getDepartamento() != null ? registration.getDepartamento() : "";

        Map<String, Object> body = new HashMap<>();
        body.put("keycloakId", registration.getUsername());
        body.put("username", registration.getUsername());
        body.put("email", email);
        body.put("nombre", nombre);
        body.put("apellidos", apellidos);
        body.put("zona", zona);
        body.put("departamento", departamento);

        restTemplate.postForEntity(
                "http://localhost:8081/internal/save-profile",
                body,
                Void.class
        );
    }
}
