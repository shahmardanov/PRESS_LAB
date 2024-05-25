package com.example.press_lab.controller;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.request.NewsCreateRequest;
import com.example.press_lab.response.NewsReadResponse;
import com.example.press_lab.service.FileStorageService;
import com.example.press_lab.service.NewsCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsCreateController {

    private final NewsCreateService newsCreateService;
    private final FileStorageService fileStorageService;

    @PostMapping("/create/add")
    public NewsReadResponse createNews(@RequestParam("title") String title,
                                       @RequestParam("content") String content,
                                       @RequestParam("newsStatus") NewsStatus status,
                                       @RequestParam("categoryStatus") CategoryStatus categoryStatus,
                                       @RequestParam("subCategoryStatus") SubCategoryStatus subCategoryStatus,
                                       @RequestParam("imageUrl") MultipartFile file) {
        String imagePath =  fileStorageService.storeFile(file);
        NewsCreateRequest request = new NewsCreateRequest(title,content,status,categoryStatus,subCategoryStatus,imagePath);
        return newsCreateService.createNews(request);
    }



}
