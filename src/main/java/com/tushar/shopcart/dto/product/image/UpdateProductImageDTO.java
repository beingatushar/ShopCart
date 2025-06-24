package com.tushar.shopcart.dto.product.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductImageDTO {
    private String imageUrl;
    private String altText;
}