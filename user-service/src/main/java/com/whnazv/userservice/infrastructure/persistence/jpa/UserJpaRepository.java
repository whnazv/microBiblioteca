package com.whnazv.userservice.infrastructure.persistence.jpa;

import com.whnazv.userservice.infrastructure.persistence.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UsersEntity, UUID> {

    Optional<UsersEntity> findByEmail(String email);

    Optional<UsersEntity> findByUsername(String username);

}
