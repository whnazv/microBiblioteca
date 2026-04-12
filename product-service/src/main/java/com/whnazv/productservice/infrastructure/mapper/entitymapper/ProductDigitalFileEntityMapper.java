package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import org.mapstruct.Mapper;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductDigitalFileEntity;
import com.whnazv.productservice.domain.model.ProductDigitalFile;

@Mapper(componentModel = "spring")
public interface ProductDigitalFileEntityMapper {
    ProductDigitalFile toModel(ProductDigitalFileEntity e);
    ProductDigitalFileEntity toEntity(ProductDigitalFile m);
}
