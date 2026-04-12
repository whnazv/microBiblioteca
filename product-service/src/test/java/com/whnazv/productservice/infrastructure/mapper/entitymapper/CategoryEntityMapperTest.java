package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import com.whnazv.productservice.domain.model.Category;
import com.whnazv.productservice.infrastructure.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityMapperTest {

    private final CategoryEntityMapper mapper = new CategoryEntityMapperImpl();

    @Test
    void shouldMapBothWays() {
        Category model = new Category();
        model.setId(1L);
        model.setName("Cat");

        CategoryEntity entity = mapper.toEntity(model);
        Category back = mapper.toModel(entity);

        assertEquals("Cat", back.getName());
    }
}
