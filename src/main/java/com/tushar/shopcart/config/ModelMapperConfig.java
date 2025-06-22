package com.tushar.shopcart.config;

import com.tushar.shopcart.dto.address.AddressDTO;
import com.tushar.shopcart.entity.AddressEntity;
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

        // Configure global settings
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        // AddressEntity → AddressDTO mapping
        modelMapper.addMappings(new PropertyMap<AddressEntity, AddressDTO>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getId());
            }
        });

        // AddressDTO → AddressEntity mapping
        modelMapper.addMappings(new PropertyMap<AddressDTO, AddressEntity>() {
            @Override
            protected void configure() {
                // Automatically create a UserEntity with just the ID set
                map().setUser(
                        source.getUserId() != null ?
                                UserEntity.builder().id(source.getUserId()).build() :
                                null
                );
            }
        });

        return modelMapper;
    }
}