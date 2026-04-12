package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import org.mapstruct.Mapper;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductEntity;
import com.whnazv.productservice.domain.model.Product;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toModel(ProductEntity e);
    ProductEntity toEntity(Product m);
}
