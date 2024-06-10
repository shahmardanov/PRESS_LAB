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
import java.util.Locale;

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
    public ResponseEntity<NewsReadResponse> getNewsById(@PathVariable(name = "news-id") Long newsId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(newsReadService.getNewsById(newsId, locale));
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<NewsReadResponse>> getAll(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(readService.getAllNews(locale));
    }

    @GetMapping("most-viewed")
    public ResponseEntity<List<NewsCardResponse>> getMostViewed(@Valid @RequestBody NewsReadByPage newsReadByPage, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(readService.getMostViewed(newsReadByPage, locale));
    }

    @GetMapping("/increment/{news-id}")
    public void incrementNewsViewCount(@PathVariable(name = "news-id") Long newsId) {
        incrementViewCount.incrementNewsViewCount(newsId);
    }

    @PostMapping("/by-content")
    public ResponseEntity<List<NewsReadResponse>> getContent(@Valid @RequestBody NewsReadRequest readRequest) {
        return ResponseEntity.ok(readService.getNewsByContent(readRequest));
    }

    @PostMapping("/by-status")
    public ResponseEntity<List<NewsCardResponse>> getStatus(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @Valid @RequestBody NewsReadByStatusRequest statusRequest) {
        return ResponseEntity.ok(readService.getNewsByStatus(statusRequest,locale));
    }

    @PostMapping("/by-category")
    public ResponseEntity<List<NewsCardResponse>> getByCategory(@Valid @RequestBody NewsReadByCategoryRequest categoryRequest, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(readService.getNewsByCategory(categoryRequest, locale));
    }

    @PostMapping("/by-subCategory")
    public ResponseEntity<List<NewsCardResponse>> getBySubCategory(@Valid @RequestBody NewsReadBySubCategoryRequest subCategoryRequest, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(readService.getNewsBySubCategory(subCategoryRequest, locale));
    }

    @PostMapping("/by-category-subCategory")
    public ResponseEntity<List<NewsCardResponse>> getByCategoryAndSubCategory(@Valid @RequestBody NewsReadByCategoryAndSubCategoryRequest categoryAndSubCategoryRequest, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(readService.getNewsByCategoryAndSubCategory(categoryAndSubCategoryRequest, locale));
    }

    @PostMapping("/read-recent")
    public ResponseEntity<List<NewsCardResponse>> getRecentNews(@Valid @RequestBody NewsReadByPage newsReadByPage, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(recentService.getRecentNews(newsReadByPage, locale));
    }

    @PostMapping("/read-recent-last24hours")
    public ResponseEntity<List<NewsCardResponse>> getRecentNewsLast24Hours(@Valid @RequestBody NewsReadByPage newsReadByPage, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(recentService.getRecentNewsLast24Hours(newsReadByPage, locale));
    }

    @GetMapping("/most-viewed-category")
    public List<Long> getMostViewedCategoryStatus() {
        return categoryService.getMostViewedCategoryStatus();
    }

    @GetMapping("/most-viewed-subCategory")
    public List<Object[]> getMostViewedSubCategoryStatus() {
        return categoryService.getMostViewedSubCategoryStatus();
    }

    @PostMapping("/most-viewed-subCategory-from-category")
    public List<Object[]> getMostViewedSubCategoryStatusFromCategory(@RequestParam Long fkCategoryId) {
        return categoryService.getMostViewedSubCategoryStatusFromCategory(fkCategoryId);
    }

    @GetMapping("/most5-viewed-categories")
    public List<Object[]> getMost5ViewedCategoryStatus() {
        return categoryService.getMost5ViewedCategoryStatus();
    }

    @GetMapping("/most5-viewed-subCategories")
    public List<Object[]> getMost5ViewedSubCategoryStatus() {
        return categoryService.getMost5ViewedSubCategoryStatus();
    }

    @PatchMapping("/update")
    public ResponseEntity<NewsUpdateResponse> update(@Valid @RequestBody NewsUpdateRequest updateRequest) {
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @DeleteMapping("/delete-all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        deleteService.deleteAll();
    }

    @DeleteMapping("/delete-by-id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@Valid @RequestBody NewsDeleteRequest deleteRequest) {
        deleteService.deleteById(deleteRequest);
    }

}
