package com.whnazv.productservice.application.service;

import com.whnazv.productservice.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductServiceUseCase {
    Mono<Product> getProductById(String id);
    Flux<Product> getAllProducts();
    Mono<Product> createProduct(Product product);
    Mono<Product> updateProduct(String id, Product product);
    Mono<Void> deleteProduct(String id);
}
