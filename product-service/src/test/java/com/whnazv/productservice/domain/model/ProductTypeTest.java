package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTypeTest {

    @Test
    void shouldContainExpectedValues() {
        assertEquals(ProductType.PHYSICAL, ProductType.valueOf("PHYSICAL"));
        assertEquals(ProductType.DIGITAL, ProductType.valueOf("DIGITAL"));
    }
}
