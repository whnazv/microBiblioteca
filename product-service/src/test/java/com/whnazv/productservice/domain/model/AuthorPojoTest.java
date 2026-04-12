package com.whnazv.productservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorPojoTest {

    @Test
    void shouldSetAndGetValues() {
        Author a = new Author();

        a.setId(1L);
        a.setFullName("Autor");

        assertEquals(1L, a.getId());
        assertEquals("Autor", a.getFullName());
    }
}
