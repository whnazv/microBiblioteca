package com.whnazv.productservice.domain.model;

import com.whnazv.productservice.domain.service.ProductStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {

    @Test
    void shouldContainExpectedValues() {
        assertEquals(ProductStatus.ACTIVE, ProductStatus.valueOf("ACTIVE"));
        assertEquals(ProductStatus.INACTIVE, ProductStatus.valueOf("INACTIVE"));
    }
}
