package com.whnazv.userservice.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Builder;
import com.whnazv.userservice.domain.model.Role;
import com.whnazv.userservice.infrastructure.persistence.entity.RolesEntity;

@Mapper(
    componentModel = "spring",
    builder = @Builder(disableBuilder = false)
)
public interface RoleMapper {

    Role toDomain(RolesEntity entity);

    RolesEntity toEntity(Role domain);
}
