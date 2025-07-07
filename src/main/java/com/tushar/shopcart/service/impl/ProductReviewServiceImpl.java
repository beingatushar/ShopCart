package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.product.review.CreateProductReviewDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
import com.tushar.shopcart.dto.product.review.UpdateProductReviewDTO;
import com.tushar.shopcart.entity.ProductEntity;
import com.tushar.shopcart.entity.ProductReviewEntity;
import com.tushar.shopcart.entity.UserEntity;
import com.tushar.shopcart.repository.ProductRepository;
import com.tushar.shopcart.repository.ProductReviewRepository;
import com.tushar.shopcart.repository.UserRepository;
import com.tushar.shopcart.service.ProductReviewService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository,
                                    UserRepository userRepository,
                                    ProductRepository productRepository,
                                    ModelMapper modelMapper) {
        this.productReviewRepository = productReviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewDTO> findAllProductReviews() {
        return productReviewRepository.findAll()
                .stream()
                .map(modelMapper::mapToProductReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductReviewDTO getProductReviewById(Long productReviewId) {
        ProductReviewEntity entity = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new EntityNotFoundException("Product review not found with id: " + productReviewId));
        return modelMapper.mapToProductReviewDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewDTO> getProductReviewsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        return productReviewRepository.findByUserId(userId)
                .stream()
                .map(modelMapper::mapToProductReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductReviewDTO> getProductReviewsByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }

        return productReviewRepository.findByProductId(productId)
                .stream()
                .map(modelMapper::mapToProductReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductReviewDTO createProductReview(CreateProductReviewDTO productReviewDTO) {
        ProductEntity product = productRepository.findById(productReviewDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productReviewDTO.getProductId()));

        UserEntity user = userRepository.findById(productReviewDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + productReviewDTO.getUserId()));

        ProductReviewEntity entity = modelMapper.mapToProductReviewEntity(productReviewDTO);
        entity.setProduct(product);
        entity.setUser(user);

        ProductReviewEntity savedEntity = productReviewRepository.save(entity);
        return modelMapper.mapToProductReviewDTO(savedEntity);
    }

    @Override
    @Transactional
    public ProductReviewDTO updateProductReview(Long productReviewId, UpdateProductReviewDTO productReviewDTO) {
        ProductReviewEntity entity = productReviewRepository.findById(productReviewId)
                .orElseThrow(() -> new EntityNotFoundException("Product review not found with id: " + productReviewId));

        modelMapper.updateProductReviewEntity(productReviewDTO, entity);
        ProductReviewEntity updatedEntity = productReviewRepository.save(entity);

        return modelMapper.mapToProductReviewDTO(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteProductReviewById(Long productReviewId) {
        if (!productReviewRepository.existsById(productReviewId)) {
            throw new EntityNotFoundException("Product review not found with id: " + productReviewId);
        }
        productReviewRepository.deleteById(productReviewId);
    }
}