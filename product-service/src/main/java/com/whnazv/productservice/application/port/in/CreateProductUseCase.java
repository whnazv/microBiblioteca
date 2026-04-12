package com.whnazv.productservice.application.port.in;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Mono;

public interface CreateProductUseCase {
    Mono<Product> create(Product product);
}
