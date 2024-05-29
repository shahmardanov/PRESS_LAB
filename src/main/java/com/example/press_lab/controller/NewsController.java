package com.example.press_lab.controller;


import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.request.news.*;
import com.example.press_lab.response.news.*;
import com.example.press_lab.service.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsCreateService createService;
    private final NewsReadService readService;
    private final NewsUpdateService updateService;
    private final NewsDeleteService deleteService;
    private final NewsCategoryService categoryService;
    private final NewsRecentService recentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NewsCreateResponse> create(@RequestBody @Valid NewsCreateRequest createRequest){
        return ResponseEntity.ok(createService.create(createRequest));
    }

    @PostMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getAll(){
        return ResponseEntity.ok(readService.getAllNews());
    }

    @PostMapping("/readByContent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getContent(@Valid @RequestBody NewsReadRequest readRequest){
        return ResponseEntity.ok(readService.getNewsByContent(readRequest));
    }

    @PostMapping("/readByStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getStatus(@Valid @RequestBody NewsReadRequest readRequest,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(readService.getNewsByStatus(readRequest, page, size));
    }

    @PostMapping("/readActiveStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getActiveStatus(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(readService.getActiveNews(page,size));
    }

    @PostMapping("/readByCategory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getByCategory(@RequestParam CategoryStatus categoryStatus,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(categoryService.getNewsByCategory(categoryStatus, page, size));
    }

    @PostMapping("/readBySubCategory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getBySubCategory(@RequestParam SubCategoryStatus subCategoryStatus,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(categoryService.getNewsBySubCategory(subCategoryStatus, page, size));
    }

    @PostMapping("/readByCategoryAndSubCategory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getByCategoryAndSubCategory(@RequestParam CategoryStatus categoryStatus,
                                                                              @RequestParam(required = false) SubCategoryStatus subCategoryStatus,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(categoryService.getNewsByCategoryAndSubCategory(categoryStatus, subCategoryStatus, page, size));
    }

    @PostMapping("/readRecentNews")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getRecentNews(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(recentService.getRecentNews(page, size));
    }

    @PostMapping("/readMostViewedCategoryStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryStatus> getMostViewedCategoryStatus(){
        return ResponseEntity.ok(categoryService.getMostViewedCategoryStatus());
    }

    @PostMapping("/readMostViewedSubCategoryStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubCategoryStatus> getMostViewedSubCategoryStatus(){
        return ResponseEntity.ok(categoryService.getMostViewedSubCategoryStatus());
    }

    @PostMapping("/readMost10ViewedCategoryStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CategoryStatus>> getMost10ViewedCategoryStatus(){
        return ResponseEntity.ok(categoryService.getMost10ViewedCategoryStatus());
    }

    @PostMapping("/readMost10ViewedSubCategoryStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SubCategoryStatus>> getMost10ViewedSubCategoryStatus(){
        return ResponseEntity.ok(categoryService.getMost10ViewedSubCategoryStatus());
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NewsUpdateResponse> update(@Valid @RequestBody NewsUpdateRequest updateRequest){
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @PostMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        deleteService.deleteAll();
    }

    @PostMapping("/deleteById")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@Valid @RequestBody NewsDeleteRequest deleteRequest){
        deleteService.deleteById(deleteRequest);
    }

}
