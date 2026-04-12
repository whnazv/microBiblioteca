package com.whnazv.productservice.infrastructure.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.whnazv.productservice.infrastructure.persistence.entity.ProductDigitalFileEntity;

public interface ProductDigitalFileRepository extends ReactiveCrudRepository<ProductDigitalFileEntity, Long> {
}
