package com.whnazv.productservice.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(schema = "product_service", name = "authors")
public class AuthorEntity {

    @Id
    private Long id;

    @Column("full_name")
    private String fullName;

    @Column("created_at")
    private LocalDateTime createdAt;
}
