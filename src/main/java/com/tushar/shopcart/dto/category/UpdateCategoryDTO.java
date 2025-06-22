package com.tushar.shopcart.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDTO {
    private String name;
    private String description;
    private String slug;
    private String imageUrl;
    private Boolean isActive;
}
