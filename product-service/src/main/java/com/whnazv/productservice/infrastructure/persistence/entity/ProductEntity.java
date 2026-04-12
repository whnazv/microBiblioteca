package com.whnazv.productservice.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(schema = "product_service", name = "products")
public class ProductEntity {

    @Id
    private Long id;

    private String isbn;
    private String title;
    private String subtitle;
    private String description;

    @Column("language_code")
    private String languageCode;

    @Column("publication_date")
    private LocalDate publicationDate;

    @Column("product_type")
    private String productType;


    private String publisher;
    private String edition;

    @Column("total_pages")
    private Integer totalPages;

    @Column("category_id")
    private Long categoryId;

    private Boolean active;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
