package com.example.press_lab.controller;

import com.example.press_lab.request.subscription.SubscriptionRequest;
import com.example.press_lab.response.subscription.SubscriptionResponse;
import com.example.press_lab.service.subscriptionService.SubscriptionCreateService;
import com.example.press_lab.service.subscriptionService.SubscriptionReadAllService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionCreateService subscriptionCreateService;

    private final SubscriptionReadAllService subscriptionReadAllService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void subscribe(@Valid @RequestBody SubscriptionRequest request) {
        subscriptionCreateService.subscribe(request);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscribers() {
        return ResponseEntity.ok(subscriptionReadAllService.getAllSubscribers());
    }

}
