package com.whnazv.userservice.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whnazv.events.v1.UserRegisteredEventV1;
import com.whnazv.userservice.application.usecase.CreateUserUseCase;
import com.whnazv.userservice.domain.model.User;
import com.whnazv.userservice.domain.port.UserRepository;
import com.whnazv.userservice.infrastructure.kafka.mapper.UserRegisteredEventToDomainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisteredListener {

    private final ObjectMapper objectMapper;
    private final UserRegisteredEventToDomainMapper mapper;
    private final CreateUserUseCase createUserUseCase;
    private final UserRepository userRepository;

    @KafkaListener(topics = "user-registered")
    public void handleUserRegistered(UserRegisteredEventV1 event) {
        try {
            log.info("Evento UserRegistered recibido: {}", 
                    objectMapper.writeValueAsString(event));

            String username = event.getUser().getUsername();

            if (userRepository.findByUsername(username).isPresent()) {
                log.warn("Usuario {} ya existe. Evento ignorado.", username);
                return;
            }

            User saved = createUserUseCase.execute(mapper.toDomain(event));
            log.info("Usuario guardado: {}", saved);

        } catch (Exception e) {
            log.error("Error procesando evento UserRegistered", e);
        }
    }
}
