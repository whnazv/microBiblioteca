package com.whnazv.productservice.domain.service;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {

    private final BigDecimal value;

    public Price(BigDecimal value) {
        Objects.requireNonNull(value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
