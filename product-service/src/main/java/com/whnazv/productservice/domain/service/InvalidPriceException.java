package com.whnazv.productservice.domain.service;

public class InvalidPriceException extends DomainException {
    public InvalidPriceException(String message) {
        super(message);
    }
}
