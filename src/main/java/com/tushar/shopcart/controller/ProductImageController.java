package com.tushar.shopcart.controller;

import com.tushar.shopcart.dto.product.image.CreateProductImageDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.dto.product.image.UpdateProductImageDTO;
import com.tushar.shopcart.service.ProductImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-images")
@Tag(name = "Product Image Management", description = "Endpoints for managing product images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public ResponseEntity<List<ProductImageDTO>> fetchAllProductImages() {
        return new ResponseEntity<>(productImageService.findAllProductImages(), HttpStatus.OK);
    }

    @GetMapping("/{productImageId}")
    public ResponseEntity<ProductImageDTO> fetchProductImageById(@PathVariable Long productImageId) {
        return new ResponseEntity<>(productImageService.getProductImageById(productImageId), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductImageDTO>> fetchProductImagesByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(productImageService.getProductImageByProductId(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductImageDTO> createProductImage(@RequestBody @Valid CreateProductImageDTO productImageDTO) {
        ProductImageDTO createdProductImage = productImageService.createProductImage(productImageDTO);
        return new ResponseEntity<>(createdProductImage, HttpStatus.CREATED);
    }

    @PutMapping("/{productImageId}")
    public ResponseEntity<ProductImageDTO> updateProductImage(
            @PathVariable Long productImageId,
            @RequestBody @Valid UpdateProductImageDTO productImageDTO) {
        ProductImageDTO updatedProductImage = productImageService.updateProductImage(productImageId, productImageDTO);
        return new ResponseEntity<>(updatedProductImage, HttpStatus.OK);
    }

    @DeleteMapping("/{productImageId}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Long productImageId) {
        productImageService.deleteProductImageById(productImageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}