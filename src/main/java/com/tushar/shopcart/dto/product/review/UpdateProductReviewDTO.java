package com.tushar.shopcart.dto.product.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductReviewDTO {
    private String comment;

    private Integer rating;
}
