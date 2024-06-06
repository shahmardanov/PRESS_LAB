package com.example.press_lab.service.subscriptionService;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Subscription;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.request.subscription.SubscriptionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubscriptionCreateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubscriptionCreateServiceTest {
    @Autowired
    private SubscriptionCreateService subscriptionCreateService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    /**
     * Method under test:
     * {@link SubscriptionCreateService#subscribe(SubscriptionRequest)}
     */
    @Test
    void testSubscribe() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEmail("jane.doe@example.org");
        subscription.setId(1L);
        when(subscriptionRepository.save(Mockito.<Subscription>any())).thenReturn(subscription);

        // Act
        subscriptionCreateService.subscribe(new SubscriptionRequest("jane.doe@example.org"));

        // Assert
        verify(subscriptionRepository).save(isA(Subscription.class));
    }
}
