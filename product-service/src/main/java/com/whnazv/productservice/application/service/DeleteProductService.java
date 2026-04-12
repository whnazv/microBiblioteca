package com.whnazv.productservice.application.service;

import com.whnazv.productservice.application.port.in.DeleteProductUseCase;
import com.whnazv.productservice.application.port.out.DeleteProductPort;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;


@Service
public class DeleteProductService implements DeleteProductUseCase {

    private final DeleteProductPort deleteProductPort;

    public DeleteProductService(DeleteProductPort deleteProductPort) {
        this.deleteProductPort = deleteProductPort;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return deleteProductPort.delete(id);
    }
}
