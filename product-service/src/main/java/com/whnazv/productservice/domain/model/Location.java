package com.whnazv.productservice.domain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Location {
    private Long id;
    private String code;
    private String name;
    private String locationType;
    private String description;
    private LocalDateTime createdAt;
    private Long parentId;
}
