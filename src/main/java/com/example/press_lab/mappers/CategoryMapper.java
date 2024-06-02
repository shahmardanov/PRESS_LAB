package com.example.press_lab.mappers;

import com.example.press_lab.entity.Category;
import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category mapRequestToEntity(CategoryRequest categoryRequest);

    CategoryResponse mapCategoryToResponse(Category category);

    Category updateCategoryToCategoryUpdateResponse(CategoryRequest categoryRequest, @MappingTarget Category category);

}
