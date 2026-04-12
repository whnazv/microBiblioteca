package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.infrastructure.mapper.entitymapper.CategoryEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.repository.CategoryRepository;
import com.whnazv.productservice.domain.model.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryPersistenceAdapter {

    private final CategoryRepository repository;
    private final CategoryEntityMapper mapper;

    public CategoryPersistenceAdapter(CategoryRepository repository, CategoryEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<Category> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    public Flux<Category> findAll() {
        return repository.findAll().map(mapper::toModel);
    }
}
