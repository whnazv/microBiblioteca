package com.example.authservice.infrastructure.service;

import com.example.authservice.domain.model.UserRegistration;
import com.example.authservice.domain.port.out.CreateUserInKeycloakPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KeycloakUserCreator implements CreateUserInKeycloakPort {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin-user}")
    private String adminUser;

    @Value("${keycloak.admin-pass}")
    private String adminPass;

    @Value("${keycloak.client-id}")
    private String clientId;

    /**
     * Obtiene un token de administrador desde Keycloak.
     */
    private String getAdminToken() {
        String url = keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=password"
                + "&client_id=" + clientId
                + "&username=" + adminUser
                + "&password=" + adminPass;

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

        if (response == null || !response.containsKey("access_token")) {
            throw new RuntimeException("No se pudo obtener token admin de Keycloak");
        }

        return (String) response.get("access_token");
    }

    /**
     * Asigna un rol de tipo Realm Role a un usuario.
     */
    private void assignRealmRole(String userId, String roleName, HttpHeaders headers) {

        ResponseEntity<Map> roleResponse = restTemplate.exchange(
                keycloakUrl + "/admin/realms/" + realm + "/roles/" + roleName,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        );

        Map<String, Object> role = roleResponse.getBody();

        if (role == null || !role.containsKey("id")) {
            throw new RuntimeException("No se pudo obtener el rol " + roleName);
        }

        HttpEntity<List<Map<String, Object>>> roleRequest =
                new HttpEntity<>(List.of(role), headers);

        restTemplate.postForEntity(
                keycloakUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/realm",
                roleRequest,
                Void.class
        );
    }

    /**
     * Crea un usuario en Keycloak y asigna el rol USER.
     */
    @Override
    public String createUser(UserRegistration registration) {

        String token = getAdminToken();

        String email = registration.getEmail() != null ? registration.getEmail() : "no-email@default.com";
        String nombre = registration.getNombre() != null ? registration.getNombre() : "Nombre";
        String apellidos = registration.getApellidos() != null ? registration.getApellidos() : "Apellidos";

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", registration.getPassword());
        credentials.put("temporary", false);

        Map<String, Object> userJson = new HashMap<>();
        userJson.put("username", registration.getUsername());
        userJson.put("enabled", true);
        userJson.put("credentials", List.of(credentials));
        userJson.put("email", email);
        userJson.put("firstName", nombre);
        userJson.put("lastName", apellidos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userJson, headers);

        ResponseEntity<Void> response;

        try {
            response = restTemplate.postForEntity(
                    keycloakUrl + "/admin/realms/" + realm + "/users",
                    request,
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error creando usuario en Keycloak: " + e.getResponseBodyAsString());
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error creando usuario: " + response.getStatusCode());
        }

        String location = response.getHeaders().getFirst("Location");
        if (location == null) {
            throw new RuntimeException("No se pudo obtener Location del usuario creado");
        }

        String userId = location.substring(location.lastIndexOf("/") + 1);

        assignRealmRole(userId, "USER", headers);

        return userId;
    }
}
