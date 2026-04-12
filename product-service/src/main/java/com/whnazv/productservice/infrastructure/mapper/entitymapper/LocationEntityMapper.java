package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import org.mapstruct.Mapper;
import com.whnazv.productservice.infrastructure.persistence.entity.LocationEntity;
import com.whnazv.productservice.domain.model.Location;

@Mapper(componentModel = "spring")
public interface LocationEntityMapper {
    Location toModel(LocationEntity e);
    LocationEntity toEntity(Location m);
}
