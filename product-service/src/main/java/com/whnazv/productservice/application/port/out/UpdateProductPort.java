package com.whnazv.productservice.application.port.out;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Mono;

public interface UpdateProductPort {
    Mono<Product> update(Product product);
}
