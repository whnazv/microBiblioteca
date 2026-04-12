package com.whnazv.productservice.domain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Author {
    private Long id;
    private String fullName;
    private LocalDateTime createdAt;
}
