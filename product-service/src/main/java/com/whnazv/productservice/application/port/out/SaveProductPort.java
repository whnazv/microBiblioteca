package com.whnazv.productservice.application.port.out;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Mono;

public interface SaveProductPort {
    Mono<Product> save(Product product);
}
