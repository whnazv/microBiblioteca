package com.whnazv.productservice.domain.model;

import java.math.BigDecimal;

public class Price {
    private final BigDecimal value;

    public Price(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
