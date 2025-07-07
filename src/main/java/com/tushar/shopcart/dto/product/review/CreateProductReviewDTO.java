package com.tushar.shopcart.dto.product.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductReviewDTO {

    private Long productId;

    private String comment;

    private Integer rating;

    private Long userId;
}
