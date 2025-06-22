package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.brand.CreateBrandDTO;
import com.tushar.shopcart.dto.brand.UpdateBrandDTO;

import java.util.List;

public interface BrandService {
    List<BrandDTO> findAll();

    BrandDTO findByName(String brandName);

    BrandDTO createBrand(CreateBrandDTO brandDTO);

    BrandDTO updateBrand(Long brandId, UpdateBrandDTO brandDTO);

    void deleteBrand(Long brandId);
}
