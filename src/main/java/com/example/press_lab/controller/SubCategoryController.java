package com.example.press_lab.controller;

import com.example.press_lab.request.subCategory.SubCategoryRequest;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import com.example.press_lab.service.subCategory.SubCategoryCreateService;
import com.example.press_lab.service.subCategory.SubCategoryDeleteService;
import com.example.press_lab.service.subCategory.SubCategoryReadAllService;
import com.example.press_lab.service.subCategory.SubCategoryUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subcategories")
public class SubCategoryController {

    private final SubCategoryCreateService categoryCreateService;

    private final SubCategoryReadAllService subCategoryReadAllService;

    private final SubCategoryDeleteService subCategoryDeleteService;

    private final SubCategoryUpdateService subCategoryUpdateService;

    @PostMapping("/create")
    public ResponseEntity<SubCategoryResponse> createSubCategory(@Valid @RequestBody SubCategoryRequest subCategoryRequest) {
        return ResponseEntity.status(CREATED).body(categoryCreateService.createCategory(subCategoryRequest));
    }

    @GetMapping("category/{fk-category-id}")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategoryByCategoryId(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @PathVariable(name = "fk-category-id") Long fkCategoryId) {
        return ResponseEntity.ok(subCategoryReadAllService.getAllSubCategoryByCategoryId(fkCategoryId, locale));
    }

    @GetMapping("all")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategory(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(subCategoryReadAllService.getAllSubCategory(locale));
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{sub-category-id}")
    public void deleteSubCategory(@PathVariable(name = "sub-category-id") Long subCategoryId) {
        subCategoryDeleteService.deleteSubCategory(subCategoryId);
    }

    @PatchMapping("/update/{sub-category-id}")
    public ResponseEntity<SubCategoryResponse> updateCategory(@PathVariable(name = "sub-category-id") Long subCategoryId, @Valid @RequestBody SubCategoryRequest categoryRequest) {
        return ResponseEntity.ok(subCategoryUpdateService.updateCategory(subCategoryId, categoryRequest));
    }

}
