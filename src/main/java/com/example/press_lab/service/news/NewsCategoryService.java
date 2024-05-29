package com.example.press_lab.service.news;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.util.NewsCategoryCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class NewsCategoryService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsCategoryCounter categoryCounter;

    public List<NewsReadResponse> getNewsByCategory(CategoryStatus categoryStatus, int page, int size){
        categoryCounter.incrementViewCount(categoryStatus);
        return newsRepository.findByStatusAndCategoryStatus(ACTIVE, categoryStatus, PageRequest.of(page,size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsBySubCategory(SubCategoryStatus subCategoryStatus, int page, int size){
        categoryCounter.incrementViewSubCount(subCategoryStatus);
        return newsRepository.findByStatusAndSubCategoryStatus(ACTIVE, subCategoryStatus, PageRequest.of(page,size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByCategoryAndSubCategory(CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus, int page, int size){
        categoryCounter.incrementViewCount(categoryStatus);
        categoryCounter.incrementViewSubCount(subCategoryStatus);
        if(subCategoryStatus == null){
            return newsRepository.findByStatusAndCategoryStatus(ACTIVE, categoryStatus, PageRequest.of(page,size))
                    .stream()
                    .map(newsMapper::mapReadToResponse)
                    .toList();
        }else{
            return newsRepository.findByStatusAndCategoryStatusAndSubCategoryStatus(ACTIVE, categoryStatus, subCategoryStatus, PageRequest.of(page,size))
                    .stream()
                    .map(newsMapper::mapReadToResponse)
                    .toList();
        }
    }

    public CategoryStatus getMostViewedCategoryStatus() {
        return categoryCounter.getMostViewedCategoryStatus();
    }

    public SubCategoryStatus getMostViewedSubCategoryStatus() {
        return categoryCounter.getMostViewedSubCategoryStatus();
    }

    public List<CategoryStatus> getMost10ViewedCategoryStatus(){
        return categoryCounter.getMost10ViewedCategoryStatus();
    }

    public List<SubCategoryStatus> getMost10ViewedSubCategoryStatus(){
        return categoryCounter.getMost10ViewedSubCategoryStatus();
    }

}
