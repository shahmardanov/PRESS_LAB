package com.example.press_lab.controller;

import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;
import com.example.press_lab.service.category.CategoryCreateService;
import com.example.press_lab.service.category.CategoryDeleteService;
import com.example.press_lab.service.category.CategoryReadAllService;
import com.example.press_lab.service.category.CategoryUpdateService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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

    @Parameter(
            name = "Accept-Language",
            required = true,
            in = ParameterIn.HEADER,
            schema = @Schema(implementation = String.class),
            example = "AZ")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategory(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(categoryReadAllService.getAllCategory(locale));
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
