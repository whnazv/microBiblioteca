package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.in.CreateProductUseCase;
import com.whnazv.productservice.application.port.out.SaveProductPort;
import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;


@Service
public class CreateProductService implements CreateProductUseCase {

    private final SaveProductPort saveProductPort;

    public CreateProductService(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }

    @Override
    public Mono<Product> create(Product product) {
        return saveProductPort.save(product);
    }
}
