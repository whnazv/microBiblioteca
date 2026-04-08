package com.whnazv.productservice.domain.model;

public class ProductName {
    private final String value;

    public ProductName(String value) {
        if (value == null || value.isBlank() || value.length() > 255) {
            throw new IllegalArgumentException("Product name must be non-empty and <= 255 characters");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
