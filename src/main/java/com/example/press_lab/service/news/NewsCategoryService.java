package com.example.press_lab.service.news;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.util.NewsCategoryCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;
import static com.example.press_lab.enums.SubCategoryStatus.ƏSASXƏBƏRLƏR;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCategoryService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsCategoryCounter categoryCounter;


//    public List<NewsReadResponse> getNewsByCategoryAndSubCategory(CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus, int page, int size){
//        if(subCategoryStatus == null){
//            subCategoryStatus = ƏSASXƏBƏRLƏR;
//        }
//        categoryCounter.incrementViewCount(categoryStatus, subCategoryStatus);
//        return newsRepository.findByStatusAndCategoryStatusAndSubCategoryStatus(ACTIVE, categoryStatus, subCategoryStatus, PageRequest.of(page,size))
//                .stream()
//                .map(newsMapper::mapReadToResponse)
//                .toList();
//    }

    public List<CategoryStatus> getMostViewedCategoryStatus() {
        return newsRepository.findMostViewedCategoryStatus(PageRequest.of(0, 1));
    }

    public List<Object[]> getMostViewedSubCategoryStatus() {
        return newsRepository.findMostViewedSubCategoryStatus();
    }

    public List<Object[]> getMostViewedSubCategoryStatusFromCategory(CategoryStatus categoryStatus) {
        return newsRepository.findMostViewedSubCategoryStatusFromCategory(categoryStatus);
    }


    public List<Object[]> getMost5ViewedCategoryStatus() {
        return newsRepository.findMost5ViewedCategoryStatus(PageRequest.of(0, 5));
    }

    public List<Object[]> getMost5ViewedSubCategoryStatus() {
        return newsRepository.findMost5ViewedSubCategoryStatus(PageRequest.of(0, 5));
    }

}
