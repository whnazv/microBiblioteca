package com.whnazv.productservice.domain.port;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> findById(String id);
    Flux<Product> findAll();
    Mono<Product> save(Product product);
    Mono<Void> deleteById(String id);
}
