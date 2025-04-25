package com.exwise.exwise.service.impl;

import com.exwise.exwise.dto.CategoryDTO;
import com.exwise.exwise.dto.response.ApiResponse;
import com.exwise.exwise.entity.Category;
import com.exwise.exwise.repository.CategoryRepository;
import com.exwise.exwise.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.exwise.exwise.entity.Expense;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setIcon(category.getIcon());
        dto.setColor(category.getColor());
        dto.setIsActive(category.isIsActive());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());
        dto.setUser_id(category.getUser_id());
        return dto;
    }

    private Category mapToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setName(dto.getName());
        category.setIcon(dto.getIcon());
        category.setColor(dto.getColor());
        category.setIsActive(dto.isIsActive());
        category.setUser_id(dto.getUser_id());
        category.setCreatedAt(dto.getCreatedAt());
        category.setUpdatedAt(dto.getUpdatedAt());
        return category;
    }

    @Override
    public ApiResponse<CategoryDTO> createCategory(CategoryDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            return new ApiResponse<>("error", 400, "Category name must not be empty", null, true);
        }
        Category category = mapToEntity(dto);
        Category saved = categoryRepository.save(category);
        return new ApiResponse<>("success", 201, "Category created successfully", mapToDTO(saved), false);

    }

    @Override
    public ApiResponse<CategoryDTO> getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryDTO dto = mapToDTO(category);
        ApiResponse<CategoryDTO> response = new ApiResponse<>("success", 200, "Category fetched", dto, false);
        return response;
    }

    @Override
    public ApiResponse<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> list = categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ApiResponse<>("success", 200, "All categories fetched", list, false);
    }

    @Override
    public ApiResponse<CategoryDTO> updateCategory(Integer id, CategoryDTO dto) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse<>("error", 404, "Category not found", null, true);
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            return new ApiResponse<>("error", 400, "Category name must not be empty", null, true);
        }

        Category category = optional.get();
        category.setName(dto.getName());
        category.setIcon(dto.getIcon());
        category.setColor(dto.getColor());

        Category updated = categoryRepository.save(category);
        return new ApiResponse<>("success", 200, "Category updated successfully", mapToDTO(updated), false);

    }

    @Override
    public ApiResponse<CategoryDTO> deleteCategory(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse<>("error", 404, "Category not found", null, true);
        }
        categoryRepository.delete(optional.get());
        return new ApiResponse<>("success", 200, "Category deleted successfully", null, false);
    }
}
