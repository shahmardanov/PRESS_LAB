package com.example.press_lab.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.press_lab.request.subscription.SubscriptionRequest;
import com.example.press_lab.service.subscriptionService.SubscriptionCreateService;
import com.example.press_lab.service.subscriptionService.SubscriptionReadAllService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SubscriptionController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubscriptionControllerTest {
    @Autowired
    private SubscriptionController subscriptionController;

    @MockBean
    private SubscriptionCreateService subscriptionCreateService;

    @MockBean
    private SubscriptionReadAllService subscriptionReadAllService;

    /**
     * Method under test:
     * {@link SubscriptionController#subscribe(SubscriptionRequest)}
     */
    @Test
    void testSubscribe() throws Exception {
        // Arrange
        doNothing().when(subscriptionCreateService).subscribe(Mockito.<SubscriptionRequest>any());

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(subscriptionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/subscriptions/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(subscriptionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link SubscriptionController#getAllSubscribers()}
     */
    @Test
    void testGetAllSubscribers() throws Exception {
        // Arrange
        when(subscriptionReadAllService.getAllSubscribers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subscriptions/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(subscriptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
