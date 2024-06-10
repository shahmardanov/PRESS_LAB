package com.example.press_lab.service.category;

import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.response.category.CategoryResponse;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CategoryReadAllService {

    private final CategoryRepository categoryRepository;

    private final LocaleResolverUtil localeResolverUtil;

    public List<CategoryResponse> getAllCategory(Locale locale) {
        return categoryRepository.findAll()
                .stream()
                .map(category -> localeResolverUtil.setForLocalCategory(category, locale))
                .toList();
    }

}
