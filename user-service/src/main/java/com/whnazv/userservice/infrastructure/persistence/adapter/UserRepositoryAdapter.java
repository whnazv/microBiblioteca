package com.whnazv.userservice.infrastructure.persistence.adapter;

import com.whnazv.userservice.domain.model.User;
import com.whnazv.userservice.domain.port.UserRepository;
import com.whnazv.userservice.infrastructure.mapper.UserMapper;
import com.whnazv.userservice.infrastructure.persistence.entity.UsersEntity;
import com.whnazv.userservice.infrastructure.persistence.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    @Transactional
    @Override
    public User save(User user) {
        log.debug("Guardando usuario: {}", user);

        UsersEntity entity = mapper.toEntity(user);
        UsersEntity saved = jpaRepository.save(entity);

        User domain = mapper.toDomain(saved);
        log.info("Usuario guardado correctamente: {}", domain);

        return domain;
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.debug("Buscando usuario por ID: {}", id);

        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Buscando usuario por email: {}", email);

        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.debug("Buscando usuario por username: {}", username);

        return jpaRepository.findByUsername(username)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        log.debug("Listando todos los usuarios");

        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        log.warn("Eliminando usuario con ID: {}", id);
        jpaRepository.deleteById(id);
    }
}
