package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.domain.model.Author;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.AuthorEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.entity.AuthorEntity;
import com.whnazv.productservice.infrastructure.persistence.repository.AuthorRepository;
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
class AuthorPersistenceAdapterTest {

    @Mock AuthorRepository repository;
    @Mock AuthorEntityMapper mapper;

    @InjectMocks
    AuthorPersistenceAdapter adapter;

    @Test
    void shouldFindById() {
        AuthorEntity e = new AuthorEntity();
        Author m = new Author();

        when(repository.findById(1L)).thenReturn(Mono.just(e));
        when(mapper.toModel(e)).thenReturn(m);

        assertNotNull(adapter.findById(1L).block());
    }

    @Test
    void shouldFindAll() {
        AuthorEntity e = new AuthorEntity();
        Author m = new Author();

        when(repository.findAll()).thenReturn(Flux.just(e));
        when(mapper.toModel(e)).thenReturn(m);

        assertEquals(1, adapter.findAll().collectList().block().size());
    }
}
