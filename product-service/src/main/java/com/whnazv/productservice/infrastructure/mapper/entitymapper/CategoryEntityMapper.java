package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import org.mapstruct.Mapper;
import com.whnazv.productservice.infrastructure.persistence.entity.CategoryEntity;
import com.whnazv.productservice.domain.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    Category toModel(CategoryEntity e);
    CategoryEntity toEntity(Category m);
}
