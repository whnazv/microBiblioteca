package com.whnazv.productservice.domain.model;

import java.time.Instant;
import java.util.List;

public class Product {

    private final String id;
    private ProductName name;
    private String description;
    private Price price;
    private Stock stock;
    private ProductStatus status;
    private ProductCategory category;
    private String ownerId;
    private String sku;
    private List<String> imageUrls;
    private final Instant createdAt;
    private Instant updatedAt;

    public Product(String id, ProductName name, String description, Price price, Stock stock,
                   ProductCategory category, String ownerId, String sku, List<String> imageUrls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.status = ProductStatus.ACTIVE;
        this.category = category;
        this.ownerId = ownerId;
        this.sku = sku;
        this.imageUrls = imageUrls;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void updatePrice(Price newPrice) {
        this.price = newPrice;
        this.updatedAt = Instant.now();
    }

    public void updateStock(Stock newStock) {
        this.stock = newStock;
        this.updatedAt = Instant.now();
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.status = ProductStatus.ACTIVE;
        this.updatedAt = Instant.now();
    }
}
