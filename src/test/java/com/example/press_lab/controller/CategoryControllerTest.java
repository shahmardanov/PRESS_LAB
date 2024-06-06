package com.example.press_lab.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;
import com.example.press_lab.service.category.CategoryCreateService;
import com.example.press_lab.service.category.CategoryDeleteService;
import com.example.press_lab.service.category.CategoryReadAllService;
import com.example.press_lab.service.category.CategoryUpdateService;
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

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryCreateService categoryCreateService;

    @MockBean
    private CategoryDeleteService categoryDeleteService;

    @MockBean
    private CategoryReadAllService categoryReadAllService;

    @MockBean
    private CategoryUpdateService categoryUpdateService;

    /**
     * Method under test: {@link CategoryController#createCategory(CategoryRequest)}
     */
    @Test
    void testCreateCategory() throws Exception {
        // Arrange
        CategoryResponse buildResult = CategoryResponse.builder().id(1L).name("Name").build();
        when(categoryCreateService.createCategory(Mockito.<CategoryRequest>any())).thenReturn(buildResult);

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(categoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/categories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\"}"));
    }

    /**
     * Method under test: {@link CategoryController#getAllCategory()}
     */
    @Test
    void testGetAllCategory() throws Exception {
        // Arrange
        when(categoryReadAllService.getAllCategory()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categories/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link CategoryController#updateCategory(Long, CategoryRequest)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        // Arrange
        CategoryResponse buildResult = CategoryResponse.builder().id(1L).name("Name").build();
        when(categoryUpdateService.updateCategory(Mockito.<Long>any(), Mockito.<CategoryRequest>any()))
                .thenReturn(buildResult);

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(categoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/categories/update/{category-id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\"}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() throws Exception {
        // Arrange
        doNothing().when(categoryDeleteService).deleteCategory(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/categories/delete/{category-id}",
                1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory2() throws Exception {
        // Arrange
        doNothing().when(categoryDeleteService).deleteCategory(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/categories/delete/{category-id}",
                1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
