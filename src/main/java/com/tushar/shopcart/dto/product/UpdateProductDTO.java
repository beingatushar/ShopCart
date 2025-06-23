package com.tushar.shopcart.dto.product;

import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.enums.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private CategoryDTO category;
    private BrandDTO brand;
    private List<ProductImageDTO> images = new ArrayList<>();
    private List<ProductAttributeDTO> attributes = new ArrayList<>();
    private ProductStatus status = ProductStatus.ACTIVE;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer version;
}
