package com.whnazv.userservice.infrastructure.kafka.mapper;

import com.whnazv.events.v1.UserRegisteredEventV1;
import com.whnazv.userservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class UserRegisteredEventToDomainMapper {

    public User toDomain(UserRegisteredEventV1 event) {

        String randomDni = UUID.randomUUID().toString().substring(0, 12);
        String randomFirst = "FN_" + UUID.randomUUID().toString().substring(0, 5);
        String randomLast = "LN_" + UUID.randomUUID().toString().substring(0, 5);
        String randomPhone = String.valueOf((int)(Math.random() * 900000000) + 100000000);

        return User.builder()
                .keycloakId(event.getUser().getKeycloakId())
                .username(event.getUser().getUsername())
                .email(event.getUser().getEmail())

                .createdAt(event.getCreatedAt())

                .dni(randomDni)
                .firstName(randomFirst)
                .lastName(randomLast)
                .phone(randomPhone)

                .status("ACTIVE")
                .updatedAt(Instant.now())

                .lastLoginAt(null)
                .deletedAt(null)
                .roles(null)

                .build();
    }
}
