package com.example.press_lab.service.subCategory;

import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryReadAllService {

    private final SubCategoryRepository subCategoryRepository;

    private final LocaleResolverUtil localeResolverUtil;

    public List<SubCategoryResponse> getAllSubCategory(Locale locale) {
        return subCategoryRepository.findAll()
                .stream()
                .map(subCategory -> localeResolverUtil.setForLocalSubCategory(subCategory, locale))
                .collect(Collectors.toList());
    }

    public List<SubCategoryResponse> getAllSubCategoryByCategoryId(Long fkCategoryId, Locale locale) {
        return subCategoryRepository.findByFkCategoryId(fkCategoryId)
                .stream()
                .map(subCategory -> localeResolverUtil.setForLocalSubCategory(subCategory, locale))
                .collect(Collectors.toList());

    }

}
