package com.whnazv.productservice.domain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductDigitalFile {
    private Long id;
    private String fileUrl;
    private String fileFormat;
    private Long fileSizeBytes;
    private String checksum;
    private LocalDateTime createdAt;
    private Long productId;
}
