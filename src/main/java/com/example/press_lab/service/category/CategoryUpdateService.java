package com.example.press_lab.service.category;

import com.example.press_lab.entity.Category;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdateService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(SubCategoryNotFoundException::new);
        Category updatedCategory = categoryMapper.updateCategoryToCategoryUpdateResponse(categoryRequest, category);
        Category savedCategory = categoryRepository.save(updatedCategory);
        return categoryMapper.mapCategoryToResponse(savedCategory);
    }

}
