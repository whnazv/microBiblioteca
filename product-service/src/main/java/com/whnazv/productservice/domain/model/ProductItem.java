package com.whnazv.productservice.domain.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductItem {
    private Long id;
    private String barcode;
    private String rfidCode;
    private String status;
    private String conditionState;
    private LocalDate acquisitionDate;
    private LocalDateTime lastRevisionAt;
    private LocalDateTime createdAt;
    private Long locationId;
    private Long productId;
}
