package com.whnazv.productservice.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteProductUseCase {
    Mono<Void> delete(Long id);
}
