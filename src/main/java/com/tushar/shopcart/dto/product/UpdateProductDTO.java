package com.tushar.shopcart.dto.product;

import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.enums.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String category;
    private String brand;
    private List<ProductImageDTO> images = new ArrayList<>();
    private List<ProductAttributeDTO> attributes = new ArrayList<>();
    private ProductStatus status;
}
