package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPojoTest {

    @Test
    void shouldSetAndGetValues() {
        Category c = new Category();

        c.setId(1L);
        c.setName("Cat");

        assertEquals(1L, c.getId());
        assertEquals("Cat", c.getName());
    }
}
