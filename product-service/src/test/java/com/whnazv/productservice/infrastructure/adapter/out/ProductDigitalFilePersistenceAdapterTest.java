package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.domain.model.ProductDigitalFile;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.ProductDigitalFileEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductDigitalFileEntity;
import com.whnazv.productservice.infrastructure.persistence.repository.ProductDigitalFileRepository;
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
class ProductDigitalFilePersistenceAdapterTest {

    @Mock ProductDigitalFileRepository repository;
    @Mock ProductDigitalFileEntityMapper mapper;

    @InjectMocks
    ProductDigitalFilePersistenceAdapter adapter;

    @Test
    void shouldFindAll() {
        when(repository.findAll()).thenReturn(Flux.just(new ProductDigitalFileEntity()));
        when(mapper.toModel(any())).thenReturn(new ProductDigitalFile());

        assertEquals(1, adapter.findAll().collectList().block().size());
    }

    @Test
    void shouldFindById() {
        when(repository.findById(1L)).thenReturn(Mono.just(new ProductDigitalFileEntity()));
        when(mapper.toModel(any())).thenReturn(new ProductDigitalFile());

        assertNotNull(adapter.findById(1L).block());
    }
}
