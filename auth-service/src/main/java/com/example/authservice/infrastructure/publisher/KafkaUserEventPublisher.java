package com.example.authservice.infrastructure.publisher;

import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.out.UserEventPublisherPort;
import com.whnazv.events.v1.UserRegisteredEventV1;
import com.whnazv.events.v1.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class KafkaUserEventPublisher implements UserEventPublisherPort {

    private final KafkaTemplate<String, UserRegisteredEventV1> kafkaTemplate;

    @Override
    public void publishUserRegistered(User user) {

        UserClient client = new UserClient(
                user.getKeycloakId(),
                user.getUsername(),
                user.getEmail()
        );

        UserRegisteredEventV1 event = new UserRegisteredEventV1(
                client,
                Instant.now()
        );

        kafkaTemplate.send("user-registered", event);
    }
}
