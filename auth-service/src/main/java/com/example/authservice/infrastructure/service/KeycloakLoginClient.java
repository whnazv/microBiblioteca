package com.example.authservice.infrastructure.service;

import com.example.authservice.application.dto.TokenResponse;
import com.example.authservice.domain.port.out.LoginUserInKeycloakPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakLoginClient implements LoginUserInKeycloakPort {

    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Override
    public TokenResponse login(String username, String password) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body =
                "client_id=" + clientId +
                "&grant_type=password" +
                "&username=" + username +
                "&password=" + password;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token",
                        request,
                        Map.class
                );

        Map<String, Object> token = response.getBody();

        return new TokenResponse(
                (String) token.get("access_token"),
                (String) token.get("refresh_token"),
                ((Number) token.get("expires_in")).longValue(),
                (String) token.get("token_type")
        );
    }
}
