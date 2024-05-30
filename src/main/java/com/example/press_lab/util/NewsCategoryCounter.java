package com.example.press_lab.util;


import com.example.press_lab.entity.News;
import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.repository.NewsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Component
@RequiredArgsConstructor
@Getter
public class NewsCategoryCounter {
    private final NewsRepository newsRepository;

    public void incrementViewCount(CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus) {
        News news = newsRepository.findByStatusAndCategoryStatusAndSubCategoryStatus(ACTIVE, categoryStatus, subCategoryStatus)
                .orElseThrow(NewsNotFoundException::new);
        news.setViewCount(news.getViewCount() + 1);
        newsRepository.save(news);
    }

}
