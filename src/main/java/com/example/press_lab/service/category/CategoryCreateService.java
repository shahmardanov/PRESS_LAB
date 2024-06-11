package com.example.press_lab.service.category;

import com.example.press_lab.entity.Category;
import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreateService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.mapRequestToEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapCategoryToResponse(savedCategory);
    }

}
