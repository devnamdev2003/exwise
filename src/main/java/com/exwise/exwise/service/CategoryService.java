package com.exwise.exwise.service;

import com.exwise.exwise.dto.CategoryDTO;
import com.exwise.exwise.dto.response.ApiResponse;

import java.util.List;

public interface CategoryService {

    ApiResponse<CategoryDTO> createCategory(CategoryDTO dto);

    ApiResponse<CategoryDTO> getCategoryById(Integer id);

    ApiResponse<List<CategoryDTO>> getAllCategories();

    ApiResponse<CategoryDTO> updateCategory(Integer id, CategoryDTO dto);

    ApiResponse<CategoryDTO> deleteCategory(Integer id);
}
