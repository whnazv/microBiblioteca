package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.infrastructure.mapper.entitymapper.ProductDigitalFileEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.repository.ProductDigitalFileRepository;
import com.whnazv.productservice.domain.model.ProductDigitalFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;


@Repository
public class ProductDigitalFilePersistenceAdapter {

    private final ProductDigitalFileRepository repository;
    private final ProductDigitalFileEntityMapper mapper;

    public ProductDigitalFilePersistenceAdapter(ProductDigitalFileRepository repository, ProductDigitalFileEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<ProductDigitalFile> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    public Flux<ProductDigitalFile> findAll() {
        return repository.findAll().map(mapper::toModel);
    }
}
