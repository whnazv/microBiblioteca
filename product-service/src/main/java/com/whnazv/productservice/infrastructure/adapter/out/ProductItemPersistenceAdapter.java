package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.infrastructure.mapper.entitymapper.ProductItemEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.repository.ProductItemRepository;
import com.whnazv.productservice.domain.model.ProductItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;


@Repository
public class ProductItemPersistenceAdapter {

    private final ProductItemRepository repository;
    private final ProductItemEntityMapper mapper;

    public ProductItemPersistenceAdapter(ProductItemRepository repository, ProductItemEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<ProductItem> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    public Flux<ProductItem> findAll() {
        return repository.findAll().map(mapper::toModel);
    }
}
