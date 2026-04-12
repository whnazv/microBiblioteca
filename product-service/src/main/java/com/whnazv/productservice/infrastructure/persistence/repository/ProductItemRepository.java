package com.whnazv.productservice.infrastructure.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductItemEntity;

public interface ProductItemRepository extends ReactiveCrudRepository<ProductItemEntity, Long> {
}
