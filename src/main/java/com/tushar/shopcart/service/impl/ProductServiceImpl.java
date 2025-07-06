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
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ProductDTO updateProduct(Long productId, UpdateProductDTO product) {
        ProductEntity productToUpdate = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(product, productToUpdate);
        return modelMapper.map(productRepository.save(productToUpdate), ProductDTO.class);
    }
}
