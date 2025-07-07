package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.product.review.CreateProductReviewDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
import com.tushar.shopcart.dto.product.review.UpdateProductReviewDTO;
import com.tushar.shopcart.dto.user.UserDTO;
import com.tushar.shopcart.entity.ProductEntity;
import com.tushar.shopcart.entity.ProductReviewEntity;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.repository.ProductRepository;
import com.tushar.shopcart.repository.ProductReviewRepository;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.ProductReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewDTO> findAllProductReviews() {
        return productReviewRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductReviewDTO getProductReviewById(Long productReviewId) {
        ProductReviewEntity entity = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new EntityNotFoundException("Product review not found with id: " + productReviewId));
        return convertToDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewDTO> getProductReviewsByUserId(Long userId) {
        return productReviewRepository.getProductReviewsByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewDTO> getProductReviewsByProductId(Long productId) {
        return productReviewRepository.getProductReviewsByProductId(productId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductReviewDTO createProductReview(CreateProductReviewDTO productReviewDTO) {
        ProductEntity product = productRepository.findById(productReviewDTO.getProductId()).orElseThrow(EntityNotFoundException::new);
        UserEntity user = userRepository.findById(productReviewDTO.getUserId()).orElseThrow(EntityNotFoundException::new);
        ProductReviewEntity entity =
                ProductReviewEntity
                        .builder()
                        .comment(productReviewDTO.getComment())
                        .rating(productReviewDTO.getRating())
                        .product(product)
                        .user(user)
                        .build();
        ProductReviewEntity savedEntity = productReviewRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public ProductReviewDTO updateProductReview(Long productReviewId, UpdateProductReviewDTO productReviewDTO) {
        ProductReviewEntity entity = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new EntityNotFoundException("Product review not found with id: " + productReviewId));

        entity.setComment(productReviewDTO.getComment());
        entity.setRating(productReviewDTO.getRating());

        ProductReviewEntity updatedEntity = productReviewRepository.save(entity);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteProductReviewById(Long productReviewId) {
        if (!productReviewRepository.existsById(productReviewId)) {
            throw new EntityNotFoundException("Product review not found with id: " + productReviewId);
        }
        productReviewRepository.deleteById(productReviewId);
    }

    private ProductReviewDTO convertToDTO(ProductReviewEntity entity) {
        return ProductReviewDTO.builder()
                .id(entity.getId())
                .comment(entity.getComment())
                .rating(entity.getRating())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .user(UserDTO.builder()
                        .id(entity.getUser().getId())
                        .build())
                .build();
    }
}