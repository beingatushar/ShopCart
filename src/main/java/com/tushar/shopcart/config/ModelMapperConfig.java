package com.tushar.shopcart.config;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.dto.product.image.ProductImageDTO;
import com.tushar.shopcart.entity.AddressEntity;
import com.tushar.shopcart.entity.ProductImageEntity;
import com.tushar.shopcart.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Global configuration
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        // AddressEntity → AddressDTO
        modelMapper.addMappings(new PropertyMap<AddressEntity, AddressDTO>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getId());
            }
        });

        // AddressDTO → AddressEntity
        modelMapper.addMappings(new PropertyMap<AddressDTO, AddressEntity>() {
            @Override
            protected void configure() {
                map().setUser(source.getUserId() != null
                        ? UserEntity.builder().id(source.getUserId()).build()
                        : null);
            }
        });

        // ProductImageEntity → ProductImageDTO
        modelMapper.addMappings(new PropertyMap<ProductImageEntity, ProductImageDTO>() {
            @Override
            protected void configure() {
                map().setProductId(source.getProduct().getId());
            }
        });

//        // ProductImageDTO → ProductImageEntity
//        modelMapper.addMappings(new PropertyMap<ProductImageDTO, ProductImageEntity>() {
//            @Override
//            protected void configure() {
//                map().setProduct(ProductEntity.builder().id(source.getProductId()).build());
//            }
//        });

//        // CreateProductImageDTO → ProductImageEntity
//        modelMapper.addMappings(new PropertyMap<CreateProductImageDTO, ProductImageEntity>() {
//            @Override
//            protected void configure() {
//                map().setProduct(ProductEntity.builder().id(source.getProductId()).build());
//            }
//        });

        return modelMapper;
    }
}
