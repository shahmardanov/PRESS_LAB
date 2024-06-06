package com.example.press_lab.service.subscriptionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.News;
import com.example.press_lab.entity.Subscription;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.service.emailService.EmailService;

import java.util.ArrayList;

import com.example.press_lab.service.util.SubscriptionUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotifySubscription.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NotifySubscriptionTest {
    @MockBean
    private EmailService emailService;

    @Autowired
    private NotifySubscription notifySubscription;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    /**
     * Method under test: {@link NotifySubscription#notifySubscribers(News)}
     */
    @Test
    void testNotifySubscribers() {
        // Arrange
        when(subscriptionRepository.findAll()).thenReturn(new ArrayList<>());

        News news = SubscriptionUtil.subscription();

        // Act
        notifySubscription.notifySubscribers(news);

        // Assert that nothing has changed
        verify(subscriptionRepository).findAll();
        assertEquals("1970-01-01", news.getCreatedAt().toLocalDate().toString());
        assertEquals("1970-01-01", news.getUpdatedAt().toLocalDate().toString());
        assertEquals("Dr", news.getTitle());
        assertEquals("Not all who wander are lost", news.getContent());
        assertEquals("The characteristics of someone or something", news.getDescription());
        assertEquals("https://example.org/example", news.getImageUrl());
        assertEquals(1L, news.getFkCategoryId().longValue());
        assertEquals(1L, news.getFkSubCategoryId().longValue());
        assertEquals(1L, news.getId().longValue());
        assertEquals(3L, news.getViewCount().longValue());
        assertEquals(NewsStatus.ACTIVE, news.getStatus());
    }

    /**
     * Method under test: {@link NotifySubscription#notifySubscribers(News)}
     */
    @Test
    void testNotifySubscribers2() {
        // Arrange
        doNothing().when(emailService).sendEmail(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

        Subscription subscription = new Subscription();
        subscription.setEmail("jane.doe@example.org");
        subscription.setId(1L);

        ArrayList<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription);
        when(subscriptionRepository.findAll()).thenReturn(subscriptionList);

        News news =SubscriptionUtil.subscription();
        // Act
        notifySubscription.notifySubscribers(news);

        // Assert that nothing has changed
        verify(emailService).sendEmail(eq("jane.doe@example.org"), eq("Yeni xəbər: Dr"), eq("Not all who wander are lost"));
        verify(subscriptionRepository).findAll();
        assertEquals("1970-01-01", news.getCreatedAt().toLocalDate().toString());
        assertEquals("1970-01-01", news.getUpdatedAt().toLocalDate().toString());
        assertEquals("Dr", news.getTitle());
        assertEquals("Not all who wander are lost", news.getContent());
        assertEquals("The characteristics of someone or something", news.getDescription());
        assertEquals("https://example.org/example", news.getImageUrl());
        assertEquals(1L, news.getFkCategoryId().longValue());
        assertEquals(1L, news.getFkSubCategoryId().longValue());
        assertEquals(1L, news.getId().longValue());
        assertEquals(3L, news.getViewCount().longValue());
        assertEquals(NewsStatus.ACTIVE, news.getStatus());
    }

    /**
     * Method under test: {@link NotifySubscription#notifySubscribers(News)}
     */
    @Test
    void testNotifySubscribers3() {
        // Arrange
        doNothing().when(emailService).sendEmail(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

        Subscription subscription = new Subscription();
        subscription.setEmail("jane.doe@example.org");
        subscription.setId(1L);

        Subscription subscription2 = new Subscription();
        subscription2.setEmail("john.smith@example.org");
        subscription2.setId(2L);

        ArrayList<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription2);
        subscriptionList.add(subscription);
        when(subscriptionRepository.findAll()).thenReturn(subscriptionList);

        News news = SubscriptionUtil.subscription();

        // Act
        notifySubscription.notifySubscribers(news);

        // Assert that nothing has changed
        verify(emailService, atLeast(1)).sendEmail(Mockito.<String>any(), eq("Yeni xəbər: Dr"),
                eq("Not all who wander are lost"));
        verify(subscriptionRepository).findAll();
        assertEquals("1970-01-01", news.getCreatedAt().toLocalDate().toString());
        assertEquals("1970-01-01", news.getUpdatedAt().toLocalDate().toString());
        assertEquals("Dr", news.getTitle());
        assertEquals("Not all who wander are lost", news.getContent());
        assertEquals("The characteristics of someone or something", news.getDescription());
        assertEquals("https://example.org/example", news.getImageUrl());
        assertEquals(1L, news.getFkCategoryId().longValue());
        assertEquals(1L, news.getFkSubCategoryId().longValue());
        assertEquals(1L, news.getId().longValue());
        assertEquals(3L, news.getViewCount().longValue());
        assertEquals(NewsStatus.ACTIVE, news.getStatus());
    }
}
