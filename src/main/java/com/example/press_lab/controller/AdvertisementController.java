package com.example.press_lab.controller;

import com.example.press_lab.request.advertisement.*;
import com.example.press_lab.response.advertisement.*;
import com.example.press_lab.service.advertisement.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advertisement")
public class AdvertisementController {
    private final AdvertisementCreateService createService;
    private final AdvertisementReadService readService;
    private final AdvertisementUpdateService updateService;
    private final AdvertisementDeleteService deleteService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AdvertisementCreateResponse> create(@Valid @RequestBody AdvertisementCreateRequest createRequest){
        return ResponseEntity.ok(createService.create(createRequest));
    }

    @PostMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AdvertisementReadResponse>> getAll(){
        return ResponseEntity.ok(readService.getAll());
    }

    @PostMapping("/readByContent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AdvertisementReadResponse>> getContent(@Valid @RequestBody AdvertisementReadRequest readRequest){
        return ResponseEntity.ok(readService.getContent(readRequest));
    }

    @PostMapping("/readByImageUrl")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AdvertisementReadResponse>> getImage(@Valid @RequestBody AdvertisementReadRequest readRequest){
        return ResponseEntity.ok(readService.getImage(readRequest));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AdvertisementUpdateResponse> update(@Valid @RequestBody AdvertisementUpdateRequest updateRequest){
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @PostMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        deleteService.deleteAll();
    }

    @PostMapping("/deleteById")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@Valid @RequestBody AdvertisementDeleteRequest deleteRequest){
        deleteService.deleteById(deleteRequest);
    }
}
