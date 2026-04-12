package com.whnazv.productservice.infrastructure.mapper.entitymapper;

import com.whnazv.productservice.domain.model.Location;
import com.whnazv.productservice.infrastructure.persistence.entity.LocationEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationEntityMapperTest {

    private final LocationEntityMapper mapper = new LocationEntityMapperImpl();

    @Test
    void shouldMapBothWays() {
        Location model = new Location();
        model.setId(1L);
        model.setCode("A1");

        LocationEntity entity = mapper.toEntity(model);
        Location back = mapper.toModel(entity);

        assertEquals("A1", back.getCode());
    }
}
