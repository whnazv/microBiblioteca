package com.example.authservice.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.client.RestTemplate;



@Configuration
public class WebClientConfig {

    @Bean
    public WebClient keycloakWebClient(@Value("${keycloak.url}") String keycloakUrl) {
        return WebClient.builder()
                .baseUrl(keycloakUrl)
                .build();
    }
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}



}
