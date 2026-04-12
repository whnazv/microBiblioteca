package com.whnazv.productservice.infrastructure.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductEntity;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
}
