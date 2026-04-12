package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.in.UpdateProductUseCase;
import com.whnazv.productservice.application.port.out.UpdateProductPort;
import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;


@Service
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductPort updateProductPort;

    public UpdateProductService(UpdateProductPort updateProductPort) {
        this.updateProductPort = updateProductPort;
    }

    @Override
    public Mono<Product> update(Long id, Product product) {
        product.setId(id);
        return updateProductPort.update(product);
    }
}
