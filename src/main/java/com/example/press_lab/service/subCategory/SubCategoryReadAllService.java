package com.example.press_lab.service.subCategory;

import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryReadAllService {

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryMapper subCategoryMapper;

    public List<SubCategoryResponse> getAllSubCategory(){
       return subCategoryRepository.findAll()
                .stream()
                .map(subCategoryMapper::mapSubCategoryToResponse)
                .collect(Collectors.toList());

    }

    public List<SubCategoryResponse> getAllSubCategoryByCategoryId(Long fkCategoryId){
        return subCategoryRepository.findByFkCategoryId(fkCategoryId)
                .stream()
                .map(subCategoryMapper::mapSubCategoryToResponse)
                .collect(Collectors.toList());

    }

}
