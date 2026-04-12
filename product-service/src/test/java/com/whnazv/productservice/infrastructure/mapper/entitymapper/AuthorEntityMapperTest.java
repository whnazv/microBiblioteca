package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import com.whnazv.productservice.domain.model.Author;
import com.whnazv.productservice.infrastructure.persistence.entity.AuthorEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorEntityMapperTest {

    private final AuthorEntityMapper mapper = new AuthorEntityMapperImpl();

    @Test
    void shouldMapBothWays() {
        Author model = new Author();
        model.setId(1L);
        model.setFullName("Autor");

        AuthorEntity entity = mapper.toEntity(model);
        Author back = mapper.toModel(entity);

        assertEquals("Autor", back.getFullName());
    }
}
