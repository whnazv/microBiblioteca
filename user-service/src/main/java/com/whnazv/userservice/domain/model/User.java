package com.whnazv.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private UUID id;

    private String dni;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private String keycloakId;

    private String status;

    private Instant createdAtUser;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastLoginAt;
    private Instant deletedAt;

    private Set<Role> roles;
}
