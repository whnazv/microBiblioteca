package com.whnazv.productservice.infrastructure.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.whnazv.productservice.infrastructure.persistence.entity.AuthorEntity;

public interface AuthorRepository extends ReactiveCrudRepository<AuthorEntity, Long> {
}
