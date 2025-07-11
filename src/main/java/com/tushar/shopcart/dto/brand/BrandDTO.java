package com.tushar.shopcart.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    Instant createdAt;
    Instant updatedAt;
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private Boolean isActive;
}
