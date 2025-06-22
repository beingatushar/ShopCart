package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.brand.CreateBrandDTO;
import com.tushar.shopcart.dto.brand.UpdateBrandDTO;
import com.tushar.shopcart.entity.BrandEntity;
import com.tushar.shopcart.repository.BrandRepository;
import com.tushar.shopcart.service.BrandService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BrandDTO findByName(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName), BrandDTO.class);
    }

    @Override
    public BrandDTO createBrand(CreateBrandDTO brandDTO) {
        BrandEntity createdBrand = new BrandEntity();
        modelMapper.map(brandDTO, createdBrand);
        return modelMapper.map(brandRepository.save(createdBrand), BrandDTO.class);
    }

    @Override
    public BrandDTO updateBrand(Long brandId, UpdateBrandDTO brandDTO) {
        BrandEntity brandToUpdate = brandRepository.findById(brandId).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(brandDTO, brandToUpdate);
        return modelMapper.map(brandRepository.save(brandToUpdate), BrandDTO.class);
    }

    @Override
    public void deleteBrand(Long brandId) {
        BrandEntity brandEntity = brandRepository.findById(brandId).orElseThrow(EntityExistsException::new);
        brandEntity.setIsActive(false);
        brandRepository.save(brandEntity);

    }
}
