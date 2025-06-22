package com.tushar.shopcart.service.impl;

import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.category.CreateCategoryDTO;
import com.tushar.shopcart.dto.category.UpdateCategoryDTO;
import com.tushar.shopcart.entity.CategoryEntity;
import com.tushar.shopcart.repository.CategoryRepository;
import com.tushar.shopcart.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDTO createCategory(CreateCategoryDTO category) {
        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
        return modelMapper.map(categoryRepository.save(categoryEntity), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long id, UpdateCategoryDTO categoryDTO) {
        CategoryEntity categoryToUpdate = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(categoryDTO, categoryToUpdate);
        return modelMapper.map(categoryRepository.save(categoryToUpdate), CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryEntity categoryToDelete = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        categoryToDelete.setIsActive(false);
        categoryRepository.save(categoryToDelete);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }
}
