package com.exwise.exwise.controller;

import com.exwise.exwise.dto.CategoryDTO;
import com.exwise.exwise.dto.response.ApiResponse;
import com.exwise.exwise.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDTO>> create(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> update(@PathVariable Integer id, @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
