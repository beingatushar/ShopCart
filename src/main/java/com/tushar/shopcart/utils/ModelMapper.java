package com.tushar.shopcart.utils;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.dto.address.CreateAddressDTO;
import com.tushar.shopcart.dto.address.UpdateAddressDTO;
import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.brand.CreateBrandDTO;
import com.tushar.shopcart.dto.brand.UpdateBrandDTO;
import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.category.CreateCategoryDTO;
import com.tushar.shopcart.dto.category.UpdateCategoryDTO;
import com.tushar.shopcart.dto.product.CreateProductDTO;
import com.tushar.shopcart.dto.product.ProductDTO;
import com.tushar.shopcart.dto.product.UpdateProductDTO;
import com.tushar.shopcart.dto.product.attribute.ProductAttributeDTO;
import com.tushar.shopcart.dto.product.image.CreateProductImageDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.dto.product.image.UpdateProductImageDTO;
import com.tushar.shopcart.dto.product.review.CreateProductReviewDTO;
import com.tushar.shopcart.dto.product.review.ProductReviewDTO;
import com.tushar.shopcart.dto.product.review.UpdateProductReviewDTO;
import com.tushar.shopcart.dto.user.CreateUserDTO;
import com.tushar.shopcart.dto.user.UpdateUserDTO;
import com.tushar.shopcart.dto.user.UserDTO;
import com.tushar.shopcart.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    // Add these methods to your existing ModelMapper class

    // Address mappings
    public AddressDTO mapToAddressDTO(AddressEntity address) {
        return AddressDTO.builder()
                .userId(address.getUser() != null ? address.getUser().getId() : null)
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .landmark(address.getLandmark())
                .isPrimary(address.getIsPrimary())
                .addressType(address.getAddressType())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }

    public AddressEntity mapToAddressEntity(CreateAddressDTO addressDTO) {
        return AddressEntity.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .postalCode(addressDTO.getPostalCode())
                .country(addressDTO.getCountry())
                .landmark(addressDTO.getLandmark())
                .isPrimary(addressDTO.getIsPrimary() != null ? addressDTO.getIsPrimary() : false)
                .addressType(addressDTO.getAddressType())
                .build();
    }

    public void updateAddressEntity(UpdateAddressDTO addressDTO, AddressEntity addressEntity) {
        if (addressDTO.getStreet() != null) {
            addressEntity.setStreet(addressDTO.getStreet());
        }
        if (addressDTO.getCity() != null) {
            addressEntity.setCity(addressDTO.getCity());
        }
        if (addressDTO.getState() != null) {
            addressEntity.setState(addressDTO.getState());
        }
        if (addressDTO.getPostalCode() != null) {
            addressEntity.setPostalCode(addressDTO.getPostalCode());
        }
        if (addressDTO.getCountry() != null) {
            addressEntity.setCountry(addressDTO.getCountry());
        }
        if (addressDTO.getLandmark() != null) {
            addressEntity.setLandmark(addressDTO.getLandmark());
        }
        if (addressDTO.getIsPrimary() != null) {
            addressEntity.setIsPrimary(addressDTO.getIsPrimary());
        }
        if (addressDTO.getAddressType() != null) {
            addressEntity.setAddressType(addressDTO.getAddressType());
        }
    }

    // Brand mappings
    public BrandDTO mapToBrandDTO(BrandEntity brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .isActive(brand.getIsActive())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .build();
    }

    public BrandEntity mapToBrandEntity(CreateBrandDTO brandDTO) {
        return BrandEntity.builder()
                .name(brandDTO.getName())
                .description(brandDTO.getDescription())
                .logoUrl(brandDTO.getLogoUrl())
                .build();
    }

    public void updateBrandEntity(UpdateBrandDTO brandDTO, BrandEntity brandEntity) {
        if (brandDTO.getName() != null) {
            brandEntity.setName(brandDTO.getName());
        }
        if (brandDTO.getDescription() != null) {
            brandEntity.setDescription(brandDTO.getDescription());
        }
        if (brandDTO.getLogoUrl() != null) {
            brandEntity.setLogoUrl(brandDTO.getLogoUrl());
        }
        if (brandDTO.getIsActive() != null) {
            brandEntity.setIsActive(brandDTO.getIsActive());
        }
    }

    // Category mappings
    public CategoryDTO mapToCategoryDTO(CategoryEntity category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .isActive(category.getIsActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public CategoryEntity mapToCategoryEntity(CreateCategoryDTO categoryDTO) {
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .imageUrl(categoryDTO.getImageUrl())
                .build();
    }

    public void updateCategoryEntity(UpdateCategoryDTO categoryDTO, CategoryEntity categoryEntity) {
        if (categoryDTO.getName() != null) {
            categoryEntity.setName(categoryDTO.getName());
        }
        if (categoryDTO.getDescription() != null) {
            categoryEntity.setDescription(categoryDTO.getDescription());
        }
        if (categoryDTO.getImageUrl() != null) {
            categoryEntity.setImageUrl(categoryDTO.getImageUrl());
        }
        if (categoryDTO.getIsActive() != null) {
            categoryEntity.setIsActive(categoryDTO.getIsActive());
        }
    }

    // Product Image mappings
    public ProductImageDTO mapToProductImageDTO(ProductImageEntity productImage) {
        return ProductImageDTO.builder()
                .id(productImage.getId())
                .imageUrl(productImage.getImageUrl())
                .altText(productImage.getAltText())
                .build();
    }

    public ProductImageEntity mapToProductImageEntity(CreateProductImageDTO productImageDTO) {
        return ProductImageEntity.builder()
                .imageUrl(productImageDTO.getImageUrl())
                .altText(productImageDTO.getAltText())
                .build();
    }

    public void updateProductImageEntity(UpdateProductImageDTO productImageDTO, ProductImageEntity productImageEntity) {
        if (productImageDTO.getImageUrl() != null) {
            productImageEntity.setImageUrl(productImageDTO.getImageUrl());
        }
        if (productImageDTO.getAltText() != null) {
            productImageEntity.setAltText(productImageDTO.getAltText());
        }
    }

    // Product Review mappings
    public ProductReviewDTO mapToProductReviewDTO(ProductReviewEntity productReview) {
        return ProductReviewDTO.builder()
                .id(productReview.getId())
                .comment(productReview.getComment())
                .rating(productReview.getRating())
                .createdAt(productReview.getCreatedAt())
                .updatedAt(productReview.getUpdatedAt())
                .user(UserDTO.builder()
                        .id(productReview.getUser().getId())
                        .build())
                .build();
    }

    public ProductReviewEntity mapToProductReviewEntity(CreateProductReviewDTO productReviewDTO) {
        return ProductReviewEntity.builder()
                .comment(productReviewDTO.getComment())
                .rating(productReviewDTO.getRating())
                .build();
    }

    public void updateProductReviewEntity(UpdateProductReviewDTO productReviewDTO, ProductReviewEntity productReviewEntity) {
        if (productReviewDTO.getComment() != null) {
            productReviewEntity.setComment(productReviewDTO.getComment());
        }
        if (productReviewDTO.getRating() != null) {
            productReviewEntity.setRating(productReviewDTO.getRating());
        }
    }

    // Product mappings
    public ProductDTO mapToProductDTO(ProductEntity product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .status(product.getStatus().name())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .category(mapToCategoryDTO(product.getCategory()))
                .brand(mapToBrandDTO(product.getBrand()))
                .images(mapProductImagesToDTO(product.getImages()))
                .attributes(mapProductAttributesToDTO(product.getAttributes()))
                .reviews(mapProductReviewsToDTO(product.getReviews()))
                .build();
    }

    public ProductEntity mapToProductEntity(CreateProductDTO productDTO) {
        return ProductEntity.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .build();
    }

    public void updateProductEntity(UpdateProductDTO productDTO, ProductEntity productEntity) {
        if (productDTO.getName() != null) {
            productEntity.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            productEntity.setDescription(productDTO.getDescription());
        }
        if (productDTO.getPrice() != null) {
            productEntity.setPrice(productDTO.getPrice());
        }
        if (productDTO.getStockQuantity() != null) {
            productEntity.setStockQuantity(productDTO.getStockQuantity());
        }
        if (productDTO.getStatus() != null) {
            productEntity.setStatus(productDTO.getStatus());
        }
    }

    private List<ProductImageDTO> mapProductImagesToDTO(List<ProductImageEntity> images) {
        if (images == null) return Collections.emptyList();

        return images.stream()
                .map(image -> ProductImageDTO.builder()
                        .id(image.getId())
                        .imageUrl(image.getImageUrl())
                        .altText(image.getAltText())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductAttributeDTO> mapProductAttributesToDTO(List<ProductAttributeEntity> attributes) {
        if (attributes == null) return Collections.emptyList();

        return attributes.stream()
                .map(attr -> ProductAttributeDTO.builder()
                        .id(attr.getId())
                        .name(attr.getName())
                        .value(attr.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProductReviewDTO> mapProductReviewsToDTO(List<ProductReviewEntity> reviews) {
        if (reviews == null) return Collections.emptyList();

        return reviews.stream()
                .map(review -> ProductReviewDTO.builder()
                        .id(review.getId())
                        .rating(review.getRating())
                        .comment(review.getComment())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // User mappings
    public UserDTO mapToUserDTO(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public UserEntity mapToUserEntity(CreateUserDTO userDTO) {
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword()) // Note: Password should be encoded before saving
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .status(userDTO.getStatus())
                .roles(userDTO.getRoles())
                .build();
    }

    public void updateUserEntity(UpdateUserDTO userDTO, UserEntity userEntity) {
        if (userDTO.getUsername() != null) {
            userEntity.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            userEntity.setPassword(userDTO.getPassword()); // Note: Password should be encoded before saving
        }
        if (userDTO.getEmail() != null) {
            userEntity.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getStatus() != null) {
            userEntity.setStatus(userDTO.getStatus());
        }
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            userEntity.setRoles(userDTO.getRoles());
        }
    }
}