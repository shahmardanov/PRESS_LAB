package com.example.press_lab.controller;

import static org.mockito.Mockito.when;

import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import com.example.press_lab.service.news.NewsCreateService;
import com.example.press_lab.service.news.NewsDeleteService;
import com.example.press_lab.service.news.NewsReadAllService;
import com.example.press_lab.service.news.NewsReadService;
import com.example.press_lab.service.news.NewsRecentService;
import com.example.press_lab.service.news.NewsUpdateService;
import com.example.press_lab.service.news.NewsViewCountIncrementService;
import com.example.press_lab.service.news.NewsViewCountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

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

@ContextConfiguration(classes = {NewsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsControllerTest {
    @Autowired
    private NewsController newsController;

    @MockBean
    private NewsCreateService newsCreateService;

    @MockBean
    private NewsDeleteService newsDeleteService;

    @MockBean
    private NewsReadAllService newsReadAllService;

    @MockBean
    private NewsReadService newsReadService;

    @MockBean
    private NewsRecentService newsRecentService;

    @MockBean
    private NewsUpdateService newsUpdateService;

    @MockBean
    private NewsViewCountIncrementService newsViewCountIncrementService;

    @MockBean
    private NewsViewCountService newsViewCountService;

    /**
     * Method under test: {@link NewsController#create(NewsCreateRequest)}
     */
    @Test
    void testCreate() throws Exception {
        // Arrange
        NewsCreateResponse.NewsCreateResponseBuilder contentResult = NewsCreateResponse.builder()
                .content("Not all who wander are lost");
        NewsCreateResponse.NewsCreateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr");
        NewsCreateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(newsCreateService.create(Mockito.<NewsCreateRequest>any())).thenReturn(buildResult);

        NewsCreateRequest newsCreateRequest = new NewsCreateRequest();
        newsCreateRequest.setContent("Not all who wander are lost");
        newsCreateRequest.setDescription("The characteristics of someone or something");
        newsCreateRequest.setFkCategoryId(1L);
        newsCreateRequest.setFkSubCategoryId(1L);
        newsCreateRequest.setImageUrl("https://example.org/example");
        newsCreateRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(newsCreateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(newsController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageUrl\":\"https://example.org/example"
                                        + "\",\"viewCount\":3,\"description\":\"The characteristics of someone or something\",\"status\":\"ACTIVE\","
                                        + "\"fkCategoryId\":1,\"fkSubCategoryId\":1,\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test: {@link NewsController#create(NewsCreateRequest)}
     */
    @Test
    void testCreate2() throws Exception {
        // Arrange
        NewsCreateResponse.NewsCreateResponseBuilder contentResult = NewsCreateResponse.builder()
                .content("Not all who wander are lost");
        NewsCreateResponse.NewsCreateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr");
        NewsCreateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(newsCreateService.create(Mockito.<NewsCreateRequest>any())).thenReturn(buildResult);

        NewsCreateRequest newsCreateRequest = new NewsCreateRequest();
        newsCreateRequest.setContent("");
        newsCreateRequest.setDescription("The characteristics of someone or something");
        newsCreateRequest.setFkCategoryId(1L);
        newsCreateRequest.setFkSubCategoryId(1L);
        newsCreateRequest.setImageUrl("https://example.org/example");
        newsCreateRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(newsCreateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(newsController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}
