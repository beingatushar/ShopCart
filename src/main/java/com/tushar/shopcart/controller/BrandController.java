package com.tushar.shopcart.controller;

import com.tushar.shopcart.dto.brand.BrandDTO;
import com.tushar.shopcart.dto.brand.CreateBrandDTO;
import com.tushar.shopcart.dto.brand.UpdateBrandDTO;
import com.tushar.shopcart.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand Management", description = "Endpoints for managing brands")
public class BrandController {
    final
    BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    ResponseEntity<List<BrandDTO>> getAllBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{brandName}")
    ResponseEntity<BrandDTO> getBrandByName(@PathVariable String brandName) {
        return new ResponseEntity<>(brandService.findByName(brandName), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<BrandDTO> createBrand(@RequestBody CreateBrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.createBrand(brandDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{brandId}")
    ResponseEntity<Void> deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{brandId}")
    ResponseEntity<BrandDTO> updateBrand(@PathVariable Long brandId, @RequestBody UpdateBrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.updateBrand(brandId, brandDTO), HttpStatus.OK);
    }

}
