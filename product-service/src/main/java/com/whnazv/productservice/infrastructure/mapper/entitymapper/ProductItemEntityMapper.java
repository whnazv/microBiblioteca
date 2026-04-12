package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import org.mapstruct.Mapper;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductItemEntity;
import com.whnazv.productservice.domain.model.ProductItem;

@Mapper(componentModel = "spring")
public interface ProductItemEntityMapper {
    ProductItem toModel(ProductItemEntity e);
    ProductItemEntity toEntity(ProductItem m);
}
