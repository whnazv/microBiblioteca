package com.whnazv.productservice.domain.service;

import java.util.Objects;

public class ProductName {

    private final String value;

    public ProductName(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank() || value.length() > 255) {
            throw new DomainException("Invalid product name");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
