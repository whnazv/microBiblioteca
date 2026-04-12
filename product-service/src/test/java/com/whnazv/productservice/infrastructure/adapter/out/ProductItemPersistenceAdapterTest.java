package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.domain.model.ProductItem;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.ProductItemEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductItemEntity;
import com.whnazv.productservice.infrastructure.persistence.repository.ProductItemRepository;
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
class ProductItemPersistenceAdapterTest {

    @Mock ProductItemRepository repository;
    @Mock ProductItemEntityMapper mapper;

    @InjectMocks
    ProductItemPersistenceAdapter adapter;

    @Test
    void shouldFindAll() {
        when(repository.findAll()).thenReturn(Flux.just(new ProductItemEntity()));
        when(mapper.toModel(any())).thenReturn(new ProductItem());

        assertEquals(1, adapter.findAll().collectList().block().size());
    }

    @Test
    void shouldFindById() {
        when(repository.findById(1L)).thenReturn(Mono.just(new ProductItemEntity()));
        when(mapper.toModel(any())).thenReturn(new ProductItem());

        assertNotNull(adapter.findById(1L).block());
    }
}
