package com.whnazv.productservice.domain.model;

import com.whnazv.productservice.domain.service.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    @Test
    void shouldContainExpectedValues() {
        assertEquals(ProductCategory.BOOK, ProductCategory.valueOf("BOOK"));
        assertEquals(ProductCategory.EBOOK, ProductCategory.valueOf("EBOOK"));
        assertEquals(ProductCategory.AUDIOBOOK, ProductCategory.valueOf("AUDIOBOOK"));
        assertEquals(ProductCategory.MAGAZINE, ProductCategory.valueOf("MAGAZINE"));
    }
}
