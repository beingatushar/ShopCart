package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.brand.CreateBrandDTO;
import com.tushar.shopcart.dto.brand.UpdateBrandDTO;
import com.tushar.shopcart.entity.BrandEntity;
import com.tushar.shopcart.exception.DuplicateResourceException;
import com.tushar.shopcart.repository.BrandRepository;
import com.tushar.shopcart.service.BrandService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream()
                .map(modelMapper::mapToBrandDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO findByName(String brandName) {
        BrandEntity brandEntity = brandRepository.findByName(brandName)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with name: " + brandName));
        return modelMapper.mapToBrandDTO(brandEntity);
    }

    @Override
    public BrandDTO createBrand(CreateBrandDTO brandDTO) {
        // Check if brand already exists
        if (brandRepository.existsByName(brandDTO.getName())) {
            throw new DuplicateResourceException("Brand already exists with name: " + brandDTO.getName());
        }

        BrandEntity brandEntity = modelMapper.mapToBrandEntity(brandDTO);
        BrandEntity savedBrand = brandRepository.save(brandEntity);
        return modelMapper.mapToBrandDTO(savedBrand);
    }

    @Override
    public BrandDTO updateBrand(Long brandId, UpdateBrandDTO brandDTO) {
        BrandEntity brandEntity = brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + brandId));

        modelMapper.updateBrandEntity(brandDTO, brandEntity);
        BrandEntity updatedBrand = brandRepository.save(brandEntity);
        return modelMapper.mapToBrandDTO(updatedBrand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        BrandEntity brandEntity = brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with id: " + brandId));

        brandEntity.setIsActive(false);
        brandRepository.save(brandEntity);

    }
}
