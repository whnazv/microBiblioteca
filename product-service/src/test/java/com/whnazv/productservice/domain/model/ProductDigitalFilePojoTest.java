package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDigitalFilePojoTest {

    @Test
    void shouldSetAndGetValues() {
        ProductDigitalFile f = new ProductDigitalFile();

        f.setId(1L);
        f.setFileUrl("url");

        assertEquals(1L, f.getId());
        assertEquals("url", f.getFileUrl());
    }
}
