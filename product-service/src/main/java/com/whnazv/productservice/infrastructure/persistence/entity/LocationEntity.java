package com.whnazv.productservice.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(schema = "product_service", name = "locations")
public class LocationEntity {

    @Id
    private Long id;

    private String code;

    private String name;

    @Column("location_type")
    private String locationType;

    private String description;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("parent_id")
    private Long parentId;
}
