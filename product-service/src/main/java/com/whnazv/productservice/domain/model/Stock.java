package com.whnazv.productservice.domain.model;

public class Stock {
    private final int value;

    public Stock(int value) {
        if (value < 0) {
            throw new InvalidStockException("Stock cannot be negative");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
