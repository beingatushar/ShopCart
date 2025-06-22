package com.tushar.shopcart.service;

import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.category.CreateCategoryDTO;
import com.tushar.shopcart.dto.category.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CreateCategoryDTO category);

    CategoryDTO updateCategory(Long id, UpdateCategoryDTO categoryDTO);

    void deleteCategory(Long id);

    List<CategoryDTO> findAll();

    CategoryDTO findByName(String name);
}
