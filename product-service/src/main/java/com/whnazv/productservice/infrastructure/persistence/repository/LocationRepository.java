package com.whnazv.productservice.infrastructure.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.whnazv.productservice.infrastructure.persistence.entity.LocationEntity;

public interface LocationRepository extends ReactiveCrudRepository<LocationEntity, Long> {
}
