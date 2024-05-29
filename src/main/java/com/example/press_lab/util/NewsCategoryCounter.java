package com.example.press_lab.util;


import com.example.press_lab.enums.CategoryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class NewsCategoryCounter {
    private final Map<CategoryStatus, Long> viewCount;

    public void incrementViewCount(CategoryStatus categoryStatus){
        viewCount.put(categoryStatus, viewCount.getOrDefault(categoryStatus, 0L) + 1);
    }

    public CategoryStatus getMostViewedCategoryStatus(){
        Map.Entry<CategoryStatus, Long> maxEntry = null;
        for (Map.Entry<CategoryStatus, Long> entry : viewCount.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry != null ? maxEntry.getKey() : null;
    }

    public List<CategoryStatus> getMost10ViewedCategoryStatus(){
        List<Map.Entry<CategoryStatus, Long>> sortedEntries = viewCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        List<CategoryStatus> top10Categories = new ArrayList<>();
        for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
            top10Categories.add(sortedEntries.get(i).getKey());
        }
        return top10Categories;
    }

}
