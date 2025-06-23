package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.product.CreateProductDTO;
import com.tushar.shopcart.dto.product.ProductDTO;
import com.tushar.shopcart.dto.product.UpdateProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> fetchAllProducts();

    ProductDTO findProductById(Long productId);

    ProductDTO createProduct(CreateProductDTO productDTO);

    void deleteProductById(Long productId);

    ProductDTO updateProduct(Long productId, UpdateProductDTO product);
}
