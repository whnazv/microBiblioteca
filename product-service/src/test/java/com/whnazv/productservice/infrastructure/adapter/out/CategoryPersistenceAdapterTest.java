package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.domain.model.Category;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.CategoryEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.entity.CategoryEntity;
import com.whnazv.productservice.infrastructure.persistence.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryPersistenceAdapterTest {

    @Mock CategoryRepository repository;
    @Mock CategoryEntityMapper mapper;

    @InjectMocks
    CategoryPersistenceAdapter adapter;

    @Test
    void shouldFindAll() {
        CategoryEntity e = new CategoryEntity();
        Category m = new Category();

        when(repository.findAll()).thenReturn(Flux.just(e));
        when(mapper.toModel(e)).thenReturn(m);

        assertEquals(1, adapter.findAll().collectList().block().size());
    }

    @Test
    void shouldFindById() {
        CategoryEntity e = new CategoryEntity();
        Category m = new Category();

        when(repository.findById(1L)).thenReturn(Mono.just(e));
        when(mapper.toModel(e)).thenReturn(m);

        assertNotNull(adapter.findById(1L).block());
    }
}
