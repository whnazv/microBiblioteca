package com.whnazv.productservice.domain.model;

import com.whnazv.productservice.domain.service.ProductName;
import com.whnazv.productservice.domain.service.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNameTest {

    @Test
    void shouldCreateValidName() {
        ProductName name = new ProductName("Libro");
        assertEquals("Libro", name.getValue());
    }

    @Test
    void shouldFailWhenBlank() {
        assertThrows(DomainException.class,
                () -> new ProductName(" "));
    }

    @Test
    void shouldFailWhenNull() {
        assertThrows(NullPointerException.class,
                () -> new ProductName(null));
    }

    @Test
    void shouldFailWhenTooLong() {
        String text = "a".repeat(256);

        assertThrows(DomainException.class,
                () -> new ProductName(text));
    }
}
