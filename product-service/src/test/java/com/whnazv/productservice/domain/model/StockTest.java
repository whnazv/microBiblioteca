package com.whnazv.productservice.domain.model;

import com.whnazv.productservice.domain.service.Stock;
import com.whnazv.productservice.domain.service.InvalidStockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void shouldCreateValidStock() {
        Stock stock = new Stock(5);
        assertEquals(5, stock.getValue());
    }

    @Test
    void shouldAllowZero() {
        Stock stock = new Stock(0);
        assertEquals(0, stock.getValue());
    }

    @Test
    void shouldFailWhenNegative() {
        assertThrows(InvalidStockException.class,
                () -> new Stock(-1));
    }
}
