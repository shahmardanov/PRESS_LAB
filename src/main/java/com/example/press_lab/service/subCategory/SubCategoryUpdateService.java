package com.example.press_lab.service.subCategory;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.request.subCategory.SubCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategoryUpdateService {

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryResponse updateCategory(Long subCategoryId, SubCategoryRequest categoryRequest) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(SubCategoryNotFoundException::new);
        SubCategory updatedSubCategory = subCategoryMapper.updateSubCategory(categoryRequest, subCategory);
        SubCategory savedSubCategory = subCategoryRepository.save(updatedSubCategory);
        return subCategoryMapper.mapSubCategoryToResponse(savedSubCategory);
    }

}
