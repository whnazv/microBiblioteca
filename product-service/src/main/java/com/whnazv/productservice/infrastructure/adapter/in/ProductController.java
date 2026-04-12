package com.whnazv.productservice.infrastructure.adapter.in;

import com.whnazv.productservice.application.port.in.CreateProductUseCase;
import com.whnazv.productservice.application.port.in.DeleteProductUseCase;
import com.whnazv.productservice.application.port.in.GetProductUseCase;
import com.whnazv.productservice.application.port.in.UpdateProductUseCase;
import com.whnazv.productservice.domain.model.Product;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(
            CreateProductUseCase createProductUseCase,
            GetProductUseCase getProductUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase
    ) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostMapping
    public Mono<Product> create(@RequestBody Product product) {
        return createProductUseCase.create(product);
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable Long id) {
        return getProductUseCase.findById(id);
    }

    @GetMapping
    public Flux<Product> findAll() {
        return getProductUseCase.findAll();
    }

    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable Long id,
                                @RequestBody Product product) {
        return updateProductUseCase.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteProductUseCase.delete(id);
    }
}
