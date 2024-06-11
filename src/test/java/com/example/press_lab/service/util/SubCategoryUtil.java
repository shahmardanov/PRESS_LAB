package com.example.press_lab.service.util;

import com.example.press_lab.entity.SubCategory;

public class SubCategoryUtil {

    private SubCategoryUtil(){

    }
    public static SubCategory subCategory(){
        SubCategory subCategory = new SubCategory();
        subCategory.setFkCategoryId(1L);
        subCategory.setId(1L);
        subCategory.setName("Name");
        return subCategory;
    }
}
