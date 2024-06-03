package com.example.press_lab.controller;

import com.example.press_lab.request.advertisement.*;
import com.example.press_lab.response.advertisement.*;
import com.example.press_lab.service.advertisement.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {
    private final AdvertisementCreateService createService;
    private final AdvertisementReadService readService;
    private final AdvertisementUpdateService updateService;
    private final AdvertisementDeleteService deleteService;

    public AdvertisementController(AdvertisementCreateService createService, AdvertisementReadService readService, AdvertisementUpdateService updateService, AdvertisementDeleteService deleteService) {
        this.createService = createService;
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @PostMapping("/create")
    public ResponseEntity<AdvertisementCreateResponse> create(@Valid @RequestBody AdvertisementCreateRequest createRequest){
        return ResponseEntity.status(CREATED).body(createService.create(createRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdvertisementReadResponse>> getAll(){
        return ResponseEntity.ok(readService.getAll());
    }

    @PostMapping("/by-content")
    public ResponseEntity<List<AdvertisementReadResponse>> getAdvertisementByContent(@Valid @RequestBody AdvertisementReadRequest readRequest){
        return ResponseEntity.ok(readService.getAdvertisementByContent(readRequest));
    }

    @PostMapping("/by-image")
    public ResponseEntity<List<AdvertisementReadResponse>> getAdvertisementByImageUrl(@Valid @RequestBody AdvertisementReadRequest readRequest){
        return ResponseEntity.ok(readService.getAdvertisementByImageUrl(readRequest));
    }

    @PatchMapping("/update")
    public ResponseEntity<AdvertisementUpdateResponse> update(@Valid @RequestBody AdvertisementUpdateRequest updateRequest){
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @DeleteMapping("/delete-all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        deleteService.deleteAll();
    }

    @DeleteMapping("/delete-by-id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@Valid @RequestBody AdvertisementDeleteRequest deleteRequest){
        deleteService.deleteById(deleteRequest);
    }

}
