package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import com.whnazv.productservice.domain.model.Product;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityMapperTest {

    private final ProductEntityMapper mapper = new ProductEntityMapperImpl();

    @Test
    void shouldMapToEntity() {
        Product model = new Product();
        model.setId(1L);
        model.setTitle("Libro");

        ProductEntity entity = mapper.toEntity(model);

        assertEquals(1L, entity.getId());
        assertEquals("Libro", entity.getTitle());
    }

    @Test
    void shouldMapToModel() {
        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setTitle("Libro");

        Product model = mapper.toModel(entity);

        assertEquals(1L, model.getId());
        assertEquals("Libro", model.getTitle());
    }
}
