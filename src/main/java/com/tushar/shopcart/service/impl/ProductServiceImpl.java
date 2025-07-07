package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.product.CreateProductDTO;
import com.tushar.shopcart.dto.product.ProductDTO;
import com.tushar.shopcart.dto.product.UpdateProductDTO;
import com.tushar.shopcart.entity.BrandEntity;
import com.tushar.shopcart.entity.CategoryEntity;
import com.tushar.shopcart.entity.ProductEntity;
import com.tushar.shopcart.enums.product.ProductStatus;
import com.tushar.shopcart.repository.BrandRepository;
import com.tushar.shopcart.repository.CategoryRepository;
import com.tushar.shopcart.repository.ProductRepository;
import com.tushar.shopcart.service.ProductService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper,
                              BrandRepository brandRepository,
                              CategoryRepository categoryRepository,
                              ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> fetchAllProducts() {
        return productRepository.findAll().stream()
                .map(modelMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findProductById(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return modelMapper.mapToProductDTO(product);
    }

    @Override
    @Transactional
    public ProductDTO createProduct(CreateProductDTO productDTO) {
        // Validate category exists
        CategoryEntity category = categoryRepository.findByName(productDTO.getCategory())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + productDTO.getCategory()));

        // Validate brand exists
        BrandEntity brand = brandRepository.findByName(productDTO.getBrand())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with name: " + productDTO.getBrand()));

        // Map and save product
        ProductEntity product = modelMapper.mapToProductEntity(productDTO);
        product.setCategory(category);
        product.setBrand(brand);

        ProductEntity savedProduct = productRepository.save(product);
        return modelMapper.mapToProductDTO(savedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        product.setStatus(ProductStatus.DELETED);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long productId, UpdateProductDTO updateDTO) {
        // Fetch existing product
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        // Update basic fields
        modelMapper.updateProductEntity(updateDTO, product);

        // Update relationships if provided
        if (updateDTO.getCategory() != null) {
            CategoryEntity category = categoryRepository.findByName(updateDTO.getCategory())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + updateDTO.getCategory()));
            product.setCategory(category);
        }

        if (updateDTO.getBrand() != null) {
            BrandEntity brand = brandRepository.findByName(updateDTO.getBrand())
                    .orElseThrow(() -> new EntityNotFoundException("Brand not found with name: " + updateDTO.getBrand()));
            product.setBrand(brand);
        }

        // Save and return
        ProductEntity updatedProduct = productRepository.save(product);
        return modelMapper.mapToProductDTO(updatedProduct);
    }
}