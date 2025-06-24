package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.product.image.CreateProductImageDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.dto.product.image.UpdateProductImageDTO;

import java.util.List;

public interface ProductImageService {
    List<ProductImageDTO> findAllProductImages();

    ProductImageDTO getProductImageById(Long productImageId);

    List<ProductImageDTO> getProductImageByProductId(Long productId);

    ProductImageDTO createProductImage(CreateProductImageDTO productImageDTO);

    ProductImageDTO updateProductImage(Long productImageId, UpdateProductImageDTO productImageDTO);

    void deleteProductImageById(Long productImageId);
}
