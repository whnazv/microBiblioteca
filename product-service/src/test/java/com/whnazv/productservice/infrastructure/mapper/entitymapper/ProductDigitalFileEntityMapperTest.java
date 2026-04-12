package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import com.whnazv.productservice.domain.model.ProductDigitalFile;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductDigitalFileEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDigitalFileEntityMapperTest {

    private final ProductDigitalFileEntityMapper mapper = new ProductDigitalFileEntityMapperImpl();

    @Test
    void shouldMapBothWays() {
        ProductDigitalFile model = new ProductDigitalFile();
        model.setId(1L);
        model.setFileUrl("url");

        ProductDigitalFileEntity entity = mapper.toEntity(model);
        ProductDigitalFile back = mapper.toModel(entity);

        assertEquals("url", back.getFileUrl());
    }
}
