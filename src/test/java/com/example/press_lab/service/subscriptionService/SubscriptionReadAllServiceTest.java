package com.example.press_lab.service.subscriptionService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.mappers.SubscriptionMapper;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.response.subscription.SubscriptionResponse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubscriptionReadAllService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubscriptionReadAllServiceTest {
    @MockBean
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private SubscriptionReadAllService subscriptionReadAllService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    /**
     * Method under test: {@link SubscriptionReadAllService#getAllSubscribers()}
     */
    @Test
    void testGetAllSubscribers() {
        // Arrange
        when(subscriptionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<SubscriptionResponse> actualAllSubscribers = subscriptionReadAllService.getAllSubscribers();

        // Assert
        verify(subscriptionRepository).findAll();
        assertTrue(actualAllSubscribers.isEmpty());
    }
}
