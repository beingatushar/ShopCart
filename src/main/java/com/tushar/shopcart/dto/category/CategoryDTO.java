package com.tushar.shopcart.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private Boolean isActive;

    private Instant createdAt;

    private Instant updatedAt;
}
