package com.example.press_lab.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.press_lab.request.subCategory.SubCategoryRequest;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import com.example.press_lab.service.subCategory.SubCategoryCreateService;
import com.example.press_lab.service.subCategory.SubCategoryDeleteService;
import com.example.press_lab.service.subCategory.SubCategoryReadAllService;
import com.example.press_lab.service.subCategory.SubCategoryUpdateService;
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

@ContextConfiguration(classes = {SubCategoryController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubCategoryControllerTest {
    @Autowired
    private SubCategoryController subCategoryController;

    @MockBean
    private SubCategoryCreateService subCategoryCreateService;

    @MockBean
    private SubCategoryDeleteService subCategoryDeleteService;

    @MockBean
    private SubCategoryReadAllService subCategoryReadAllService;

    @MockBean
    private SubCategoryUpdateService subCategoryUpdateService;

    /**
     * Method under test:
     * {@link SubCategoryController#getAllSubCategoryByCategoryId(Long)}
     */
    @Test
    void testGetAllSubCategoryByCategoryId() throws Exception {
        // Arrange
        when(subCategoryReadAllService.getAllSubCategoryByCategoryId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/subcategories/category/{fk-category-id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(subCategoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SubCategoryController#getAllSubCategory()}
     */
    @Test
    void testGetAllSubCategory() throws Exception {
        // Arrange
        when(subCategoryReadAllService.getAllSubCategory()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subcategories/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(subCategoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link SubCategoryController#createSubCategory(SubCategoryRequest)}
     */
    @Test
    void testCreateSubCategory() throws Exception {
        // Arrange
        SubCategoryResponse buildResult = SubCategoryResponse.builder().fkCategoryId(1L).id(1L).name("Name").build();
        when(subCategoryCreateService.createCategory(Mockito.<SubCategoryRequest>any())).thenReturn(buildResult);

        SubCategoryRequest subCategoryRequest = new SubCategoryRequest();
        subCategoryRequest.setFkCategoryId(1L);
        subCategoryRequest.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subCategoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/subcategories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(subCategoryController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"fkCategoryId\":1}"));
    }

    /**
     * Method under test: {@link SubCategoryController#deleteSubCategory(Long)}
     */
    @Test
    void testDeleteSubCategory() throws Exception {
        // Arrange
        doNothing().when(subCategoryDeleteService).deleteSubCategory(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/subcategories/{sub-category-id}",
                1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(subCategoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link SubCategoryController#deleteSubCategory(Long)}
     */
    @Test
    void testDeleteSubCategory2() throws Exception {
        // Arrange
        doNothing().when(subCategoryDeleteService).deleteSubCategory(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/subcategories/{sub-category-id}",
                1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(subCategoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link SubCategoryController#updateCategory(Long, SubCategoryRequest)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        // Arrange
        SubCategoryResponse buildResult = SubCategoryResponse.builder().fkCategoryId(1L).id(1L).name("Name").build();
        when(subCategoryUpdateService.updateCategory(Mockito.<Long>any(), Mockito.<SubCategoryRequest>any()))
                .thenReturn(buildResult);

        SubCategoryRequest subCategoryRequest = new SubCategoryRequest();
        subCategoryRequest.setFkCategoryId(1L);
        subCategoryRequest.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(subCategoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/subcategories/update/{sub-category-id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(subCategoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"fkCategoryId\":1}"));
    }
}
