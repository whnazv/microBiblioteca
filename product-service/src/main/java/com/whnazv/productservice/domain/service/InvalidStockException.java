package com.whnazv.productservice.domain.service;

public class InvalidStockException extends DomainException {
    public InvalidStockException(String message) {
        super(message);
    }
}
