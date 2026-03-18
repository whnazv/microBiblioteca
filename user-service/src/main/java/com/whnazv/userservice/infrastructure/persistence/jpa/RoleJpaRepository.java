package com.whnazv.userservice.infrastructure.persistence.jpa;

import com.whnazv.userservice.infrastructure.persistence.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<RolesEntity, UUID> {
    Optional<RolesEntity> findByName(String name);
}
