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

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsCreateService createService;
    private final NewsReadService readService;
    private final NewsUpdateService updateService;
    private final NewsDeleteService deleteService;
    private final NewsViewCountService categoryService;
    private final NewsRecentService recentService;

    //
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
        return ResponseEntity.ok(readService.getNewsByCategory(categoryStatus, page, size));
    }

    @PostMapping("/readBySubCategory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getBySubCategory(@RequestParam SubCategoryStatus subCategoryStatus,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(readService.getNewsBySubCategory(subCategoryStatus, page, size));
    }

    @PostMapping("/readByCategoryAndSubCategory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getByCategoryAndSubCategory(@RequestParam CategoryStatus categoryStatus,
                                                                              @RequestParam(required = false) SubCategoryStatus subCategoryStatus,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(readService.getNewsByCategoryAndSubCategory(categoryStatus, subCategoryStatus, page, size));
    }

    @PostMapping("/readRecentNews")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getRecentNews(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(recentService.getRecentNews(page, size));
    }

    @PostMapping("/readRecentNewsLast24Hours")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getRecentNewsLast24Hours(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(recentService.getRecentNewsLast24Hours(page, size));
    }

    @PostMapping("/mostViewedCategory")
    public List<CategoryStatus> getMostViewedCategoryStatus() {
        return categoryService.getMostViewedCategoryStatus();
    }

    @PostMapping("/mostViewedSubCategory")
    public List<Object[]> getMostViewedSubCategoryStatus() {
        return categoryService.getMostViewedSubCategoryStatus();
    }

    @PostMapping("/mostViewedSubCategoryFromCategory")
    public List<Object[]>  getMostViewedSubCategoryStatusFromCategory(@RequestParam CategoryStatus categoryStatus) {
        return categoryService.getMostViewedSubCategoryStatusFromCategory(categoryStatus);
    }

    @PostMapping("/most5ViewedCategories")
    public List<Object[]> getMost5ViewedCategoryStatus() {
        return categoryService.getMost5ViewedCategoryStatus();
    }

    @PostMapping("/most5ViewedSubCategories")
    public List<Object[]> getMost5ViewedSubCategoryStatus() {
        return categoryService.getMost5ViewedSubCategoryStatus();
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
