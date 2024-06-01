package com.example.press_lab.service.subCategory;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.request.subCategory.SubCategoryRequest;
import com.example.press_lab.request.subCategory.SubCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubCategoryCreateService {

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryMapper subcategoryMapper;

    public SubCategoryResponse createCategory(SubCategoryRequest request) {
        SubCategory subCategory = subcategoryMapper.mapRequestToEntity(request);
        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
        return subcategoryMapper.mapSubCategoryToResponse(savedSubCategory);
    }

}
