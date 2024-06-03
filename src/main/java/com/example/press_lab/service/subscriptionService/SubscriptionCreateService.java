package com.example.press_lab.service.subscriptionService;

import com.example.press_lab.entity.Subscription;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.request.subscription.SubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionCreateService {

    private final SubscriptionRepository subscriptionRepository;

    public void subscribe(SubscriptionRequest request){
        Subscription subscription = new Subscription();
        subscription.setEmail(request.getEmail());
        subscriptionRepository.save(subscription);
    }

}
