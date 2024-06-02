package com.example.press_lab.mappers;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.request.subCategory.SubCategoryRequest;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SubCategoryMapper {

    SubCategory mapRequestToEntity(SubCategoryRequest categoryRequest);

    SubCategoryResponse mapSubCategoryToResponse(SubCategory subCategory);

    SubCategory updateSubCategory(SubCategoryRequest categoryRequest, @MappingTarget SubCategory subCategory);

}
