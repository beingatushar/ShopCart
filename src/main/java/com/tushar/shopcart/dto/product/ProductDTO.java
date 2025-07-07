package com.tushar.shopcart.dto.product;

import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.product.attribute.ProductAttributeDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
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
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
    private CategoryDTO category;
    private BrandDTO brand;

    private List<ProductImageDTO> images;
    private List<ProductAttributeDTO> attributes;
    private List<ProductReviewDTO> reviews;
}
