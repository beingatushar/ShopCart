package com.tushar.shopcart.controller;

import com.tushar.shopcart.dto.category.CategoryDTO;
import com.tushar.shopcart.dto.category.CreateCategoryDTO;
import com.tushar.shopcart.dto.category.UpdateCategoryDTO;
import com.tushar.shopcart.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
@Tag(name = "Category Management", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody @Valid UpdateCategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDTO> findByName(@PathVariable String categoryName) {
        return new ResponseEntity<>(categoryService.findByName(categoryName), HttpStatus.OK);
    }

}
