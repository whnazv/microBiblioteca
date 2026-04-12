package com.whnazv.productservice.domain.model;

import com.whnazv.productservice.domain.service.Price;
import com.whnazv.productservice.domain.service.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void shouldCreateValidPrice() {
        Price price = new Price(new BigDecimal("10.50"));
        assertEquals(new BigDecimal("10.50"), price.getValue());
    }

    @Test
    void shouldFailWhenZero() {
        assertThrows(InvalidPriceException.class,
                () -> new Price(BigDecimal.ZERO));
    }

    @Test
    void shouldFailWhenNegative() {
        assertThrows(InvalidPriceException.class,
                () -> new Price(new BigDecimal("-1")));
    }

    @Test
    void shouldFailWhenNull() {
        assertThrows(NullPointerException.class,
                () -> new Price(null));
    }
}
