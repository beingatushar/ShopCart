package com.tushar.shopcart.dto.product.review;

import com.tushar.shopcart.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDTO {

    private Long id;

    private String comment;

    private Integer rating;

    private UserDTO user;

    private Instant createdAt;

    private Instant updatedAt;
}
