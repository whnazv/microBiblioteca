package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationPojoTest {

    @Test
    void shouldSetAndGetValues() {
        Location l = new Location();

        l.setId(1L);
        l.setCode("A1");

        assertEquals(1L, l.getId());
        assertEquals("A1", l.getCode());
    }
}
