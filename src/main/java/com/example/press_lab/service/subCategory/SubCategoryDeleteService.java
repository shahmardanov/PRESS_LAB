package com.example.press_lab.service.subCategory;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategoryDeleteService {

    private final SubCategoryRepository categoryRepository;

    public void deleteSubCategory(Long subCategoryId){
        SubCategory subCategory = categoryRepository.findById(subCategoryId).orElseThrow(SubCategoryNotFoundException::new);
        categoryRepository.delete(subCategory);
    }

}
