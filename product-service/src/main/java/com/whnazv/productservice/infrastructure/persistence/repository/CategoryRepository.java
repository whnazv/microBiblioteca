package com.whnazv.productservice.infrastructure.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.whnazv.productservice.infrastructure.persistence.entity.CategoryEntity;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryEntity, Long> {
}
