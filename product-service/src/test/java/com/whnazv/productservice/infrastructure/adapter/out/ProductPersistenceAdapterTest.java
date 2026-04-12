package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.domain.model.Product;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.ProductEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductEntity;
import com.whnazv.productservice.infrastructure.persistence.repository.ProductRepository;
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
class ProductPersistenceAdapterTest {

    @Mock ProductRepository repository;
    @Mock ProductEntityMapper mapper;

    @InjectMocks
    ProductPersistenceAdapter adapter;

    @Test
    void shouldSave() {
        Product model = new Product();
        ProductEntity entity = new ProductEntity();

        when(mapper.toEntity(model)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.toModel(entity)).thenReturn(model);

        Product result = adapter.save(model).block();

        assertNotNull(result);
        verify(repository).save(entity);
    }

    @Test
    void shouldFindById() {
        Product model = new Product();
        ProductEntity entity = new ProductEntity();

        when(repository.findById(1L)).thenReturn(Mono.just(entity));
        when(mapper.toModel(entity)).thenReturn(model);

        assertNotNull(adapter.findById(1L).block());
    }

    @Test
    void shouldFindAll() {
        ProductEntity entity = new ProductEntity();
        Product model = new Product();

        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(mapper.toModel(entity)).thenReturn(model);

        assertEquals(1, adapter.findAll().collectList().block().size());
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(1L)).thenReturn(Mono.empty());

        adapter.delete(1L).block();

        verify(repository).deleteById(1L);
    }
}
