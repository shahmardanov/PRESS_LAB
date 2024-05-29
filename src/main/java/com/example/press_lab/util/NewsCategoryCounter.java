package com.example.press_lab.util;


import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Getter
public class NewsCategoryCounter {
    private final Map<CategoryStatus, Long> viewCount;
    private final Map<SubCategoryStatus, Long> viewSubCount;


    public void incrementViewCount(CategoryStatus categoryStatus){
        viewCount.put(categoryStatus, viewCount.getOrDefault(categoryStatus, 0L) + 1);
    }

    public void incrementViewSubCount(SubCategoryStatus subCategoryStatus){
        viewSubCount.put(subCategoryStatus, viewSubCount.getOrDefault(subCategoryStatus, 0L) + 1);
    }

    public CategoryStatus getMostViewedCategoryStatus() {
        return viewCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public SubCategoryStatus getMostViewedSubCategoryStatus() {
        return viewSubCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<CategoryStatus> getMost10ViewedCategoryStatus() {
        return viewCount.entrySet().stream()
                .sorted(Map.Entry.<CategoryStatus, Long>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<SubCategoryStatus> getMost10ViewedSubCategoryStatus() {
        return viewSubCount.entrySet().stream()
                .sorted(Map.Entry.<SubCategoryStatus, Long>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();
    }



}
