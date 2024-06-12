package com.example.press_lab.controller;

import com.example.press_lab.request.kart.*;
import com.example.press_lab.response.kart.*;
import com.example.press_lab.service.kart.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kart")
public class KartController {
    private final KartCreateService createService;
    private final KartReadService readService;
    private final KartUpdateService updateService;
    private final KartDeleteService deleteService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KartCreateResponse> create(@Valid @RequestBody KartCreateRequest createRequest){
        return ResponseEntity.ok(createService.create(createRequest));
    }

    @PostMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<KartReadResponse>> getAll(){
        return ResponseEntity.ok(readService.getAll());
    }

    @PostMapping("/readByContent")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<KartReadResponse>> getKartByContent(@Valid @RequestBody KartReadRequest readRequest){
        return ResponseEntity.ok(readService.getKartByContent(readRequest));
    }

    @PostMapping("/readByFkNewsId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<KartReadResponse>> getKartByFkNewsId(@Valid @RequestBody KartReadRequest readRequest){
        return ResponseEntity.ok(readService.getKartByFkNewsId(readRequest));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KartUpdateResponse> update(@Valid @RequestBody KartUpdateRequest updateRequest){
        return ResponseEntity.ok(updateService.update(updateRequest));
    }

    @PostMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        deleteService.deleteAll();
    }

    @PostMapping("/deleteById")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@Valid @RequestBody KartDeleteRequest deleteRequest){
        deleteService.deleteById(deleteRequest);
    }

}
