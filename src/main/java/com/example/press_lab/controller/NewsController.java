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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsCreateService createService;
    private final NewsReadService readService;
    private final NewsUpdateService updateService;
    private final NewsDeleteService deleteService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NewsCreateResponse> create(@RequestBody @Valid NewsCreateRequest createRequest){
        return ResponseEntity.ok(createService.create(createRequest));
    }

    @PostMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getAll(){
        return ResponseEntity.ok(readService.getAll());
    }

    @PostMapping("/readByContent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getContent(@Valid @RequestBody NewsReadRequest readRequest){
        return ResponseEntity.ok(readService.getContent(readRequest));
    }

    @PostMapping("/readByStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getStatus(@Valid @RequestBody NewsReadRequest readRequest){
        return ResponseEntity.ok(readService.getStatus(readRequest));
    }

    @PostMapping("/readActiveStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getActiveStatus(){
        return ResponseEntity.ok(readService.getActiveStatus());
    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<NewsReadResponse> getNewsById(@PathVariable int id) {
        Optional<NewsReadResponse> news = Optional.ofNullable(readService.getNewsById(id));
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
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
