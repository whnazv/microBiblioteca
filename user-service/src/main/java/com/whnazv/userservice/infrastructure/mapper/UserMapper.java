package com.whnazv.userservice.infrastructure.mapper;

import com.whnazv.userservice.domain.model.User;
import com.whnazv.userservice.infrastructure.persistence.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UsersEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "keycloakId", source = "keycloakId")
    @Mapping(target = "createdAtUser", source = "createdAtUser")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "lastLoginAt", source = "lastLoginAt")
    @Mapping(target = "deletedAt", source = "deletedAt")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    UsersEntity toEntity(User domain);
}
