package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.in.GetProductUseCase;
import com.whnazv.productservice.application.port.out.LoadProductPort;
import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Flux;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class GetProductService implements GetProductUseCase {

    private final LoadProductPort loadProductPort;

    public GetProductService(LoadProductPort loadProductPort) {
        this.loadProductPort = loadProductPort;
    }

    @Override
    public Mono<Product> findById(Long id) {
        return loadProductPort.findById(id);
    }

    @Override
    public Flux<Product> findAll() {
        return loadProductPort.findAll();
    }
}
