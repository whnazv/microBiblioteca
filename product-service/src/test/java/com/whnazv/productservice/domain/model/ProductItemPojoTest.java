package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductItemPojoTest {

    @Test
    void shouldSetAndGetValues() {
        ProductItem i = new ProductItem();

        i.setId(1L);
        i.setBarcode("123");

        assertEquals(1L, i.getId());
        assertEquals("123", i.getBarcode());
    }
}
