package com.example.press_lab.service.util;

import com.example.press_lab.entity.Category;

public class CategoryUtil {

   private CategoryUtil(){

   }

   public static Category category(){
       Category category = new Category();
       category.setId(1L);
       category.setName("Name");
       return category;
   }
}
