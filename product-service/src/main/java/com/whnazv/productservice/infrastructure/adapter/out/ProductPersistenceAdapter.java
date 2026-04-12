package com.whnazv.productservice.infrastructure.adapter.out;

import com.whnazv.productservice.application.port.out.*;
import com.whnazv.productservice.domain.model.Product;
import com.whnazv.productservice.infrastructure.mapper.entitymapper.ProductEntityMapper;
import com.whnazv.productservice.infrastructure.persistence.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;


@Repository
public class ProductPersistenceAdapter implements 
        SaveProductPort, 
        UpdateProductPort, 
        DeleteProductPort, 
        LoadProductPort {

    private final ProductRepository repository;
    private final ProductEntityMapper mapper;

    public ProductPersistenceAdapter(ProductRepository repository, ProductEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(mapper.toEntity(product))
                .map(mapper::toModel);
    }

    @Override
    public Mono<Product> update(Product product) {
        return repository.save(mapper.toEntity(product))
                .map(mapper::toModel);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Product> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public Flux<Product> findAll() {
        return repository.findAll()
                .map(mapper::toModel);
    }
}
