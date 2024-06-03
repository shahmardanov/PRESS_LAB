package com.example.press_lab.service.news;

import com.example.press_lab.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class NewsViewCountService {
    private final NewsRepository newsRepository;

    public List<Long> getMostViewedCategoryStatus() {
        return newsRepository.findMostViewedCategoryStatus(PageRequest.of(0, 1));
    }

    public List<Object[]> getMostViewedSubCategoryStatus() {
        return newsRepository.findMostViewedSubCategoryStatus();
    }

    public List<Object[]> getMostViewedSubCategoryStatusFromCategory(Long fkCategoryId) {
        return newsRepository.findMostViewedSubCategoryStatusFromCategory(fkCategoryId);
    }

    public List<Object[]> getMost5ViewedCategoryStatus() {
        return newsRepository.findMost5ViewedCategoryStatus(PageRequest.of(0, 5));
    }

    public List<Object[]> getMost5ViewedSubCategoryStatus() {
        return newsRepository.findMost5ViewedSubCategoryStatus(PageRequest.of(0, 5));
    }

}
