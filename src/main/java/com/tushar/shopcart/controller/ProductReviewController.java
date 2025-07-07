package com.tushar.shopcart.controller;

import com.tushar.shopcart.dto.product.review.CreateProductReviewDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
import com.tushar.shopcart.dto.product.review.UpdateProductReviewDTO;
import com.tushar.shopcart.service.ProductReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Product Reviews", description = "API for managing product reviews")
public class ProductReviewController {

    private final ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    // Product-centric review endpoints
    @GetMapping("/products/{productId}/reviews")
    @Operation(summary = "Get all reviews for a product")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved product reviews")
    public ResponseEntity<List<ProductReviewDTO>> getProductReviewsByProductId(
            @PathVariable Long productId) {
        return ResponseEntity.ok(productReviewService.getProductReviewsByProductId(productId));
    }

    @GetMapping("/products/reviews/{reviewId}")
    @Operation(summary = "Get a specific product review by ID")
    @ApiResponse(responseCode = "200", description = "Review found")
    @ApiResponse(responseCode = "404", description = "Review not found")
    public ResponseEntity<ProductReviewDTO> getProductReviewById(
            @PathVariable Long reviewId) {
        return ResponseEntity.ok(productReviewService.getProductReviewById(reviewId));
    }

    @PostMapping("/products/{productId}/reviews")
    @Operation(summary = "Create a new product review")
    @ApiResponse(responseCode = "201", description = "Review created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<ProductReviewDTO> createProductReview(
            @PathVariable Long productId,
            @RequestBody CreateProductReviewDTO reviewDTO) {
        reviewDTO.setProductId(productId);
        ProductReviewDTO createdReview = productReviewService.createProductReview(reviewDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdReview);
    }

    @PutMapping("/products/reviews/{reviewId}")
    @Operation(summary = "Update an existing product review")
    @ApiResponse(responseCode = "200", description = "Review updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Review not found")
    public ResponseEntity<ProductReviewDTO> updateProductReview(
            @PathVariable Long reviewId,
            @RequestBody UpdateProductReviewDTO reviewDTO) {
        return ResponseEntity.ok(productReviewService.updateProductReview(reviewId, reviewDTO));
    }

    @DeleteMapping("/products/reviews/{reviewId}")
    @Operation(summary = "Delete a product review")
    @ApiResponse(responseCode = "204", description = "Review deleted successfully")
    @ApiResponse(responseCode = "404", description = "Review not found")
    public ResponseEntity<Void> deleteProductReview(
            @PathVariable Long reviewId) {
        productReviewService.deleteProductReviewById(reviewId);
        return ResponseEntity.noContent().build();
    }

    // User-centric review endpoints
    @GetMapping("/users/{userId}/reviews")
    @Operation(summary = "Get all reviews by a user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user reviews")
    public ResponseEntity<List<ProductReviewDTO>> getProductReviewsByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(productReviewService.getProductReviewsByUserId(userId));
    }
}