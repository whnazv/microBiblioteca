package com.whnazv.productservice.application.port.out;

import reactor.core.publisher.Mono;

public interface DeleteProductPort {
    Mono<Void> delete(Long id);
}
