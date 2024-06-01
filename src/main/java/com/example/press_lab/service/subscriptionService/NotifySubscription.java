package com.example.press_lab.service.subscriptionService;

import com.example.press_lab.entity.News;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.service.emailService.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.press_lab.constant.SubscriptionConstant.NEWS_SUBJECT_MESSAGE;


@Service
@RequiredArgsConstructor
public class NotifySubscription {

    private final EmailService emailService;

    private final SubscriptionRepository subscriptionRepository;

    public void notifySubscribers(News news) {
        String subject = NEWS_SUBJECT_MESSAGE + news.getTitle();
        String text = news.getContent();
        subscriptionRepository.findAll()
                .forEach(subscription -> emailService.sendEmail(subscription.getEmail(), subject, text));
    }

}
