package com.example.press_lab.controller;

import com.example.press_lab.response.NewsCardResponse;
import com.example.press_lab.response.NewsReadResponse;
import com.example.press_lab.service.NewsReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsReadController {

    private final NewsReadService newsReadService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsReadResponse> getNewsById(@PathVariable int id) {
        Optional<NewsReadResponse> news = Optional.ofNullable(newsReadService.getNewsById(id));
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping
    public List<NewsReadResponse> getAllNews() {
        return newsReadService.getAllNews();
    }

    @GetMapping("/page")
    public Page<NewsCardResponse> getNewsPage(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10")int size){
        return newsReadService.getNewsPage(page,size);
    }
}
