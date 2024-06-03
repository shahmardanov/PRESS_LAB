package com.example.press_lab.service.category;

import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.response.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryReadAllService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategory(){
       return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToResponse)
                .collect(Collectors.toList());

    }

}
