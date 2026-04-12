package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductPojoTest {

    @Test
    void shouldSetAndGetValues() {
        Product p = new Product();

        p.setId(1L);
        p.setTitle("Test");

        assertEquals(1L, p.getId());
        assertEquals("Test", p.getTitle());
    }
}
