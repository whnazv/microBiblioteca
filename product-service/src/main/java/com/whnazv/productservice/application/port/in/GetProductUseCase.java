package com.whnazv.productservice.application.port.in;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetProductUseCase {
    Mono<Product> findById(Long id);
    Flux<Product> findAll();
}
