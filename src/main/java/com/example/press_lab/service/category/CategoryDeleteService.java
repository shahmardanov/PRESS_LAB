package com.example.press_lab.service.category;

import com.example.press_lab.entity.Category;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDeleteService {

    private final CategoryRepository categoryRepository;

    public void deleteCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(SubCategoryNotFoundException::new);
        categoryRepository.delete(category);
    }

}
