package com.example.press_lab.controller;


import com.example.press_lab.request.news.*;
import com.example.press_lab.response.news.*;
import com.example.press_lab.service.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsCreateService createService;

    private final NewsReadAllService readService;

    private final NewsUpdateService updateService;

    private final NewsDeleteService deleteService;

    private final NewsViewCountService categoryService;

    private final NewsRecentService recentService;

    private final NewsReadService newsReadService;

    private final NewsViewCountIncrementService incrementViewCount;

    @PostMapping("/create")
    public ResponseEntity<NewsCreateResponse> create(@RequestBody @Valid NewsCreateRequest createRequest) {
        return ResponseEntity.status(CREATED).body(createService.create(createRequest));
    }

    @GetMapping("/{news-id}")
    public ResponseEntity<NewsReadResponse> getNewsById(@PathVariable(name = "news-id") Long newsId) {
        return ResponseEntity.ok(newsReadService.getNewsById(newsId));
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<NewsReadResponse>> getAll() {
        return ResponseEntity.ok(readService.getAllNews());
    }

    @GetMapping("most-viewed")
    public ResponseEntity<List<NewsCardResponse>> getMostViewed(@Valid @RequestBody NewsReadByPage newsReadByPage){
        return ResponseEntity.ok(readService.getMostViewed(newsReadByPage));
    }

    @GetMapping("/increment/{news-id}")
    public void incrementNewsViewCount(@PathVariable(name = "news-id") Long newsId) {
        incrementViewCount.incrementNewsViewCount(newsId);
    }

    @PostMapping("/read-by-Content")
    public ResponseEntity<List<NewsReadResponse>> getContent(@Valid @RequestBody NewsReadRequest readRequest) {
        return ResponseEntity.ok(readService.getNewsByContent(readRequest));
    }

    @PostMapping("/read-by-status")
    public ResponseEntity<List<NewsCardResponse>> getStatus(@Valid @RequestBody NewsReadByStatusRequest statusRequest) {
        return ResponseEntity.ok(readService.getNewsByStatus(statusRequest));
    }

    @PostMapping("/read-by-category")
    public ResponseEntity<List<NewsCardResponse>> getByCategory(@Valid @RequestBody NewsReadByCategoryRequest categoryRequest) {
        return ResponseEntity.ok(readService.getNewsByCategory(categoryRequest));
    }

    @PostMapping("/read-by-subCategory")
    public ResponseEntity<List<NewsCardResponse>> getBySubCategory(@Valid @RequestBody NewsReadBySubCategoryRequest subCategoryRequest) {
        return ResponseEntity.ok(readService.getNewsBySubCategory(subCategoryRequest));
    }

    @PostMapping("/read-by-category-subCategory")
    public ResponseEntity<List<NewsCardResponse>> getByCategoryAndSubCategory(@Valid @RequestBody NewsReadByCategoryAndSubCategoryRequest categoryAndSubCategoryRequest) {
        return ResponseEntity.ok(readService.getNewsByCategoryAndSubCategory(categoryAndSubCategoryRequest));
    }

    @PostMapping("/read-recent")
    public ResponseEntity<List<NewsCardResponse>> getRecentNews(@Valid @RequestBody NewsReadByPage newsReadByPage) {
        return ResponseEntity.ok(recentService.getRecentNews(newsReadByPage));
    }

    @PostMapping("/read-recent-last24hours")
    public ResponseEntity<List<NewsCardResponse>> getRecentNewsLast24Hours(@Valid @RequestBody NewsReadByPage newsReadByPage) {
        return ResponseEntity.ok(recentService.getRecentNewsLast24Hours(newsReadByPage));
    }

    @GetMapping("/mostViewedCategory")
    public List<Long> getMostViewedCategoryStatus() {
        return categoryService.getMostViewedCategoryStatus();
    }

    @GetMapping("/mostViewedSubCategory")
    public List<Object[]> getMostViewedSubCategoryStatus() {
        return categoryService.getMostViewedSubCategoryStatus();
    }

    @PostMapping("/mostViewedSubCategoryFromCategory")
    public List<Object[]> getMostViewedSubCategoryStatusFromCategory(@RequestParam Long fkCategoryId) {
        return categoryService.getMostViewedSubCategoryStatusFromCategory(fkCategoryId);
    }

    @GetMapping("/most5ViewedCategories")
    public List<Object[]> getMost5ViewedCategoryStatus() {
        return categoryService.getMost5ViewedCategoryStatus();
    }

    @GetMapping("/most5ViewedSubCategories")
    public List<Object[]> getMost5ViewedSubCategoryStatus() {
        return categoryService.getMost5ViewedSubCategoryStatus();
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NewsUpdateResponse> update(@Valid @RequestBody NewsUpdateRequest updateRequest) {
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        deleteService.deleteAll();
    }

    @DeleteMapping("/deleteById")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@Valid @RequestBody NewsDeleteRequest deleteRequest) {
        deleteService.deleteById(deleteRequest);
    }

}
