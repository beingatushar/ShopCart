package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.category.CreateCategoryDTO;
import com.tushar.shopcart.dto.category.UpdateCategoryDTO;
import com.tushar.shopcart.entity.CategoryEntity;
import com.tushar.shopcart.exception.DuplicateResourceException;
import com.tushar.shopcart.repository.CategoryRepository;
import com.tushar.shopcart.service.CategoryService;
import com.tushar.shopcart.utils.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO createCategory(CreateCategoryDTO categoryDTO) {
        // Check if category already exists
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new DuplicateResourceException("Category already exists with name: " + categoryDTO.getName());
        }

        CategoryEntity categoryEntity = modelMapper.mapToCategoryEntity(categoryDTO);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return modelMapper.mapToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long id, UpdateCategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        modelMapper.updateCategoryEntity(categoryDTO, categoryEntity);
        CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
        return modelMapper.mapToCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        categoryEntity.setIsActive(false);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(modelMapper::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with name: " + name));
        return modelMapper.mapToCategoryDTO(categoryEntity);
    }
}