package com.whnazv.productservice.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(schema = "product_service", name = "product_items")
public class ProductItemEntity {

    @Id
    private Long id;

    private String barcode;

    @Column("rfid_code")
    private String rfidCode;

    private String status;

    @Column("condition_state")
    private String conditionState;

    @Column("acquisition_date")
    private LocalDate acquisitionDate;

    @Column("last_revision_at")
    private LocalDateTime lastRevisionAt;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("location_id")
    private Long locationId;

    @Column("product_id")
    private Long productId;
}
