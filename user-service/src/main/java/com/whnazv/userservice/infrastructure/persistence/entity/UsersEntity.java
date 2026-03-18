package com.whnazv.userservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsersEntity {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(length = 12)
    private String dni;

    @Column(length = 100)
    private String firstName;

    @Column(length = 150)
    private String lastName;

    @Column(length = 150)
    private String email;

    @Column(nullable = false, unique=true, length = 100)
    private String username;

    @Column(length = 20)
    private String phone;

    @Column(name = "keycloak_id", length = 50)
    private String keycloakId;

    private Instant createdAtUser;

    @Column(name = "user_status", nullable = false, length = 20)
    private String status;

    private Instant lastLoginAt;

    private Instant deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
