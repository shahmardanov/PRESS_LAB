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

    @PostMapping("/readContent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getContent(@Valid @RequestBody NewsReadRequest readRequest){
        return ResponseEntity.ok(readService.getContent(readRequest));
    }

    @PostMapping("/readStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getStatus(@Valid @RequestBody NewsReadRequest readRequest){
        return ResponseEntity.ok(readService.getStatus(readRequest));
    }

    @PostMapping("/readActiveStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NewsReadResponse>> getActiveStatus(@Valid @RequestBody NewsReadRequest readRequest){
        return ResponseEntity.ok(readService.getActiveStatus(readRequest));
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
