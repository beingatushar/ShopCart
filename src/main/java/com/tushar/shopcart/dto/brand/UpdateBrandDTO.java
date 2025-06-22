package com.tushar.shopcart.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandDTO {
    private String name;
    private String description;
    private String logoUrl;
    private Boolean isActive;
}
