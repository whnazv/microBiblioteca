package com.whnazv.productservice.infrastructure.adapter.out;

import org.springframework.stereotype.Repository;

import com.whnazv.productservice.infrastructure.mapper.entitymapper.AuthorEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.repository.AuthorRepository;
import com.whnazv.productservice.domain.model.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AuthorPersistenceAdapter {

    private final AuthorRepository repository;
    private final AuthorEntityMapper mapper;

    public AuthorPersistenceAdapter(AuthorRepository repository, AuthorEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Author> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    public Flux<Author> findAll() {
        return repository.findAll().map(mapper::toModel);
    }
}
