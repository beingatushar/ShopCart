package com.tushar.shopcart.dto.product;

import com.tushar.shopcart.entity.ProductAttributeEntity;
import com.tushar.shopcart.entity.ProductImageEntity;
import com.tushar.shopcart.entity.ProductReviewEntity;
import com.tushar.shopcart.enums.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private Long brandId;
    private List<ProductImageEntity> images;
    private List<ProductAttributeEntity> attributes;
    private List<ProductReviewEntity> reviews;
    private ProductStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
