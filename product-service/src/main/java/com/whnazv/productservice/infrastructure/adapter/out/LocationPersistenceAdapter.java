package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.infrastructure.mapper.entitymapper.LocationEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.repository.LocationRepository;
import com.whnazv.productservice.domain.model.Location;
import reactor.core.publisher.Flux;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class LocationPersistenceAdapter {

    private final LocationRepository repository;
    private final LocationEntityMapper mapper;

    public LocationPersistenceAdapter(LocationRepository repository, LocationEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Location> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    public Flux<Location> findAll() {
        return repository.findAll().map(mapper::toModel);
    }
}
