package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.product.review.CreateProductReviewDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
import com.tushar.shopcart.dto.product.review.UpdateProductReviewDTO;

import java.util.List;

public interface ProductReviewService {
    List<ProductReviewDTO> findAllProductReviews();

    ProductReviewDTO getProductReviewById(Long productReviewId);

    List<ProductReviewDTO> getProductReviewsByUserId(Long userId);

    List<ProductReviewDTO> getProductReviewsByProductId(Long productId);

    ProductReviewDTO createProductReview(CreateProductReviewDTO productReviewDTO);

    ProductReviewDTO updateProductReview(Long productReviewId, UpdateProductReviewDTO productReviewDTO);

    void deleteProductReviewById(Long productReviewId);
}
