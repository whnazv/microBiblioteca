package com.whnazv.productservice.domain.port;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Mono;

public interface ProductEventPublisher {
    Mono<Void> publishProductCreated(Product product);
    Mono<Void> publishProductUpdated(Product product);
    Mono<Void> publishProductDeleted(String productId);
}
