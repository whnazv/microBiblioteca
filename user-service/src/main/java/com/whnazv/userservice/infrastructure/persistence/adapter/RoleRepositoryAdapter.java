package com.whnazv.userservice.infrastructure.persistence.adapter;

import com.whnazv.userservice.domain.model.Role;
import com.whnazv.userservice.domain.port.RoleRepository;
import com.whnazv.userservice.infrastructure.mapper.RoleMapper;
import com.whnazv.userservice.infrastructure.persistence.entity.RolesEntity;
import com.whnazv.userservice.infrastructure.persistence.jpa.RoleJpaRepository;
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
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository jpaRepository;
    private final RoleMapper mapper;

    /**
     * Guarda un rol en la base de datos.
     * Se ejecuta dentro de una transacción.
     */
    @Transactional
    @Override
    public Role save(Role role) {
        log.debug("Guardando rol en base de datos: {}", role);

        RolesEntity entity = mapper.toEntity(role);
        RolesEntity saved = jpaRepository.save(entity);

        Role domain = mapper.toDomain(saved);
        log.info("Rol guardado correctamente: {}", domain);

        return domain;
    }

    @Override
    public Optional<Role> findById(UUID id) {
        log.debug("Buscando rol por ID: {}", id);

        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByName(String name) {
        log.debug("Buscando rol por nombre: {}", name);

        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        log.debug("Listando todos los roles");

        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
