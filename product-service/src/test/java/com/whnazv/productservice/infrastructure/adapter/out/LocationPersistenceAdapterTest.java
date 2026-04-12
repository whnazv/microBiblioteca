package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.domain.model.Location;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.LocationEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.entity.LocationEntity;
import com.whnazv.productservice.infrastructure.persistence.repository.LocationRepository;
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
class LocationPersistenceAdapterTest {

    @Mock LocationRepository repository;
    @Mock LocationEntityMapper mapper;

    @InjectMocks
    LocationPersistenceAdapter adapter;

    @Test
    void shouldFindAll() {
        LocationEntity e = new LocationEntity();
        Location m = new Location();

        when(repository.findAll()).thenReturn(Flux.just(e));
        when(mapper.toModel(e)).thenReturn(m);

        assertEquals(1, adapter.findAll().collectList().block().size());
    }

    @Test
    void shouldFindById() {
        when(repository.findById(1L)).thenReturn(Mono.just(new LocationEntity()));
        when(mapper.toModel(any())).thenReturn(new Location());

        assertNotNull(adapter.findById(1L).block());
    }
}
