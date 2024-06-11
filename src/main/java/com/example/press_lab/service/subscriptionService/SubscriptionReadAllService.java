package com.example.press_lab.service.subscriptionService;

import com.example.press_lab.mappers.SubscriptionMapper;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.response.subscription.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionReadAllService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionMapper subscriptionMapper;

    public List<SubscriptionResponse> getAllSubscribers() {
        return subscriptionRepository.findAll()
                .stream()
                .map(subscriptionMapper::mapSubscriptionToResponse)
                .collect(Collectors.toList());
    }

}
