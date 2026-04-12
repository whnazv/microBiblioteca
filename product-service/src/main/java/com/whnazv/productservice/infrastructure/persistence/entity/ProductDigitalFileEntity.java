package com.whnazv.productservice.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(schema = "product_service", name = "product_digital_files")
public class ProductDigitalFileEntity {

    @Id
    private Long id;

    @Column("file_url")
    private String fileUrl;

    @Column("file_format")
    private String fileFormat;

    @Column("file_size_bytes")
    private Long fileSizeBytes;

    private String checksum;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("product_id")
    private Long productId;
}
