package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import com.whnazv.productservice.domain.model.ProductItem;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductItemEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductItemEntityMapperTest {

    private final ProductItemEntityMapper mapper = new ProductItemEntityMapperImpl();

    @Test
    void shouldMapBothWays() {
        ProductItem model = new ProductItem();
        model.setId(1L);
        model.setBarcode("123");

        ProductItemEntity entity = mapper.toEntity(model);
        ProductItem back = mapper.toModel(entity);

        assertEquals("123", back.getBarcode());
    }
}
