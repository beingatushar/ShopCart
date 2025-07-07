package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.product.image.CreateProductImageDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.dto.product.image.UpdateProductImageDTO;
import com.tushar.shopcart.entity.ProductEntity;
import com.tushar.shopcart.entity.ProductImageEntity;
import com.tushar.shopcart.repository.ProductImageRepository;
import com.tushar.shopcart.repository.ProductRepository;
import com.tushar.shopcart.service.ProductImageService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository,
                                   ProductRepository productRepository,
                                   ModelMapper modelMapper) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageDTO> findAllProductImages() {
        return productImageRepository.findAll()
                .stream()
                .map(modelMapper::mapToProductImageDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImageDTO getProductImageById(Long productImageId) {
        ProductImageEntity productImageEntity = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new EntityNotFoundException("Product Image not found with id: " + productImageId));
        return modelMapper.mapToProductImageDTO(productImageEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageDTO> getProductImageByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }

        return productImageRepository.findByProductId(productId)
                .stream()
                .map(modelMapper::mapToProductImageDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductImageDTO createProductImage(CreateProductImageDTO productImageDTO) {
        ProductEntity product = productRepository.findById(productImageDTO.getProductId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Product with ID " + productImageDTO.getProductId() + " not found"
                ));

        ProductImageEntity productImageEntity = modelMapper.mapToProductImageEntity(productImageDTO);
        productImageEntity.setProduct(product);

        ProductImageEntity savedImage = productImageRepository.save(productImageEntity);
        return modelMapper.mapToProductImageDTO(savedImage);
    }

    @Override
    @Transactional
    public ProductImageDTO updateProductImage(Long productImageId, UpdateProductImageDTO productImageDTO) {
        ProductImageEntity existingImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new EntityNotFoundException("Product Image not found with id: " + productImageId));

        modelMapper.updateProductImageEntity(productImageDTO, existingImage);
        ProductImageEntity updatedImage = productImageRepository.save(existingImage);

        return modelMapper.mapToProductImageDTO(updatedImage);
    }

    @Override
    @Transactional
    public void deleteProductImageById(Long productImageId) {
        if (!productImageRepository.existsById(productImageId)) {
            throw new EntityNotFoundException("Product Image not found with id: " + productImageId);
        }
        productImageRepository.deleteById(productImageId);
    }
}