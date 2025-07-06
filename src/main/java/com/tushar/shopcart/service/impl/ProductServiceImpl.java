package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.product.CreateProductDTO;
import com.tushar.shopcart.dto.product.ProductAttributeDTO;
import com.tushar.shopcart.dto.product.ProductDTO;
import com.tushar.shopcart.dto.product.UpdateProductDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
import com.tushar.shopcart.entity.*;
import com.tushar.shopcart.enums.product.ProductStatus;
import com.tushar.shopcart.repository.BrandRepository;
import com.tushar.shopcart.repository.CategoryRepository;
import com.tushar.shopcart.repository.ProductRepository;
import com.tushar.shopcart.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    final
    ModelMapper modelMapper;
    final
    BrandRepository brandRepository;
    final
    CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> fetchAllProducts() {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    public ProductDTO findProductById(Long productId) {
        return modelMapper.map(productRepository.findById(productId).orElseThrow(EntityNotFoundException::new), ProductDTO.class);
    }

    @Transactional
    public ProductDTO createProduct(CreateProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStockQuantity(productDTO.getStockQuantity());
        String categoryName = productDTO.getCategory();
        CategoryEntity categoryEntity = categoryRepository.findByName(categoryName).orElseThrow(EntityNotFoundException::new);
        product.setCategory(categoryEntity);
        String brandName = productDTO.getBrand();
        BrandEntity brandEntity = brandRepository.findByName(brandName).orElseThrow(EntityNotFoundException::new);
        product.setBrand(brandEntity);
        System.out.println(product);
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        product.setStatus(ProductStatus.DELETED);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long productId, UpdateProductDTO updateDTO) {
        // 1. Fetch existing product
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        // 2. Update basic fields
        product.setName(updateDTO.getName() != null ? updateDTO.getName() : product.getName());
        product.setDescription(updateDTO.getDescription() != null ? updateDTO.getDescription() : product.getDescription());
        product.setPrice(updateDTO.getPrice() != null ? updateDTO.getPrice() : product.getPrice());
        product.setStockQuantity(updateDTO.getStockQuantity() != null ? updateDTO.getStockQuantity() : product.getStockQuantity());
        product.setStatus(updateDTO.getStatus() != null ? updateDTO.getStatus() : product.getStatus());

        // 3. Handle relationships (if needed)
        // For example, if you need to update category/brand by name:
        // if (updateDTO.getCategory() != null) {
        //     CategoryEntity category = categoryRepository.findByName(updateDTO.getCategory())
        //         .orElseThrow(...);
        //     product.setCategory(category);
        // }

        // 4. Save the updated product
        ProductEntity updatedProduct = productRepository.save(product);

        // 5. Map to DTO manually
        return ProductDTO.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .stockQuantity(updatedProduct.getStockQuantity())
                .status(updatedProduct.getStatus().name())
                .createdAt(updatedProduct.getCreatedAt())
                .updatedAt(updatedProduct.getUpdatedAt())
                .images(mapImagesToDTO(updatedProduct.getImages()))
                .attributes(mapAttributesToDTO(updatedProduct.getAttributes()))
                .reviews(mapReviewsToDTO(updatedProduct.getReviews()))
                .build();
    }

    // Helper methods for nested collections
    private List<ProductImageDTO> mapImagesToDTO(List<ProductImageEntity> images) {
        if (images == null) return Collections.emptyList();

        return images.stream()
                .map(image -> ProductImageDTO.builder()
                        .id(image.getId())
                        .imageUrl(image.getImageUrl())
//                        .isPrimary(image.getIsPrimary())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductAttributeDTO> mapAttributesToDTO(List<ProductAttributeEntity> attributes) {
        if (attributes == null) return Collections.emptyList();

        return attributes.stream()
                .map(attr -> ProductAttributeDTO.builder()
                        .id(attr.getId())
                        .name(attr.getName())
                        .value(attr.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductReviewDTO> mapReviewsToDTO(List<ProductReviewEntity> reviews) {
        if (reviews == null) return Collections.emptyList();

        return reviews.stream()
                .map(review -> ProductReviewDTO.builder()
                        .id(review.getId())
                        .rating(review.getRating())
                        .comment(review.getComment())
//                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
