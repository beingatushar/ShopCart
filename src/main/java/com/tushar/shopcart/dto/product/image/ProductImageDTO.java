package com.tushar.shopcart.dto.product.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private Long productId;
    private Long id;
    private String imageUrl;
    private String altText;
}