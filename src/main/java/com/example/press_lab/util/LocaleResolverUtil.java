package com.example.press_lab.util;

import com.example.press_lab.entity.Category;
import com.example.press_lab.entity.News;
import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.response.category.CategoryResponse;
import com.example.press_lab.response.news.NewsCardResponse;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LocaleResolverUtil {

    private final NewsMapper newsMapper;

    private final CategoryMapper categoryMapper;

    private final SubCategoryMapper subCategoryMapper;

    public NewsCardResponse setForLocal(News news, Locale locale) {
        NewsCardResponse response = newsMapper.mapReadToCardResponse(news);
        switch (locale.getLanguage()) {
            case "az" -> response.setTitle(news.getTitle());
            case "ru" -> response.setTitle(news.getTitleRu());
            case "en" -> response.setTitle(news.getTitleEn());
        }
        return response;
    }

    public NewsReadResponse setForLocalId(News news, Locale locale) {
        NewsReadResponse response = newsMapper.mapReadToResponse(news);
        switch (locale.getLanguage()) {
            case "az" -> {
                response.setTitle(news.getTitle());
                response.setContent(news.getContent());
                response.setDescription(news.getDescription());
            }
            case "ru" -> {
                response.setTitle(news.getTitleRu());
                response.setContent(news.getContentRu());
                response.setDescription(news.getDescriptionRu());
            }
            case "en" ->{
                response.setTitle(news.getTitleEn());
                response.setContent(news.getContentEn());
                response.setDescription(news.getDescriptionEn());
            }
        }
        return response;
    }

    public CategoryResponse setForLocalCategory(Category category, Locale locale) {
        CategoryResponse response = categoryMapper.mapCategoryToResponse(category);
        switch (locale.getLanguage()) {
            case "az" -> response.setName(category.getName());
            case "ru" -> response.setName(category.getNameRu());
            case "en" -> response.setName(category.getNameEn());
        }
        return response;
    }

    public SubCategoryResponse setForLocalSubCategory(SubCategory subCategory, Locale locale) {
        SubCategoryResponse response = subCategoryMapper.mapSubCategoryToResponse(subCategory);
        switch (locale.getLanguage()) {
            case "az" -> response.setName(subCategory.getName());
            case "ru" -> response.setName(subCategory.getNameRu());
            case "en" -> response.setName(subCategory.getNameEn());
        }
        return response;
    }

}
