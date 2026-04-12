package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import org.mapstruct.Mapper;
import com.whnazv.productservice.infrastructure.persistence.entity.AuthorEntity;
import com.whnazv.productservice.domain.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorEntityMapper {
    Author toModel(AuthorEntity e);
    AuthorEntity toEntity(Author m);
}
