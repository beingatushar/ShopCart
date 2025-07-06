package com.tushar.shopcart.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;

    private String category;

    private String brand;
}