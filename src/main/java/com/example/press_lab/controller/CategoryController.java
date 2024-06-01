package com.example.press_lab.controller;

import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.request.category.CategoryResponse;
import com.example.press_lab.service.category.CategoryCreateService;
import com.example.press_lab.service.category.CategoryDeleteService;
import com.example.press_lab.service.category.CategoryReadAllService;
import com.example.press_lab.service.category.CategoryUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryCreateService categoryCreateService;

    private final CategoryReadAllService categoryReadAllService;

    private final CategoryUpdateService categoryUpdateService;

    private final CategoryDeleteService categoryDeleteService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.status(CREATED).body(categoryCreateService.createCategory(categoryRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return ResponseEntity.ok(categoryReadAllService.getAllCategory());
    }

    @PatchMapping("/update/{category-id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable(name = "category-id") Long categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryUpdateService.updateCategory(categoryId, categoryRequest));
    }

    @DeleteMapping("/delete/{category-id}")
    public void deleteCategory(@PathVariable(name = "category-id") Long categoryId) {
        categoryDeleteService.deleteCategory(categoryId);
    }

}
