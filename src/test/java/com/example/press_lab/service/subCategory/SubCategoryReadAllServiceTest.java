package com.example.press_lab.service.subCategory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.response.subCategory.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubCategoryReadAllService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubCategoryReadAllServiceTest {
    @MockBean
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    private SubCategoryReadAllService subCategoryReadAllService;

    @MockBean
    private SubCategoryRepository subCategoryRepository;

    /**
     * Method under test: {@link SubCategoryReadAllService#getAllSubCategory()}
     */
    @Test
    void testGetAllSubCategory() {
        // Arrange
        when(subCategoryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<SubCategoryResponse> actualAllSubCategory = subCategoryReadAllService.getAllSubCategory();

        // Assert
        verify(subCategoryRepository).findAll();
        assertTrue(actualAllSubCategory.isEmpty());
    }

    /**
     * Method under test:
     * {@link SubCategoryReadAllService#getAllSubCategoryByCategoryId(Long)}
     */
    @Test
    void testGetAllSubCategoryByCategoryId() {
        // Arrange
        when(subCategoryRepository.findByFkCategoryId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        List<SubCategoryResponse> actualAllSubCategoryByCategoryId = subCategoryReadAllService
                .getAllSubCategoryByCategoryId(1L);

        // Assert
        verify(subCategoryRepository).findByFkCategoryId(eq(1L));
        assertTrue(actualAllSubCategoryByCategoryId.isEmpty());
    }
}
