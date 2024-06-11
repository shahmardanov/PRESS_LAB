package com.example.press_lab.service.category;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.response.category.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryReadAllService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CategoryReadAllServiceTest {
    @MockBean
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryReadAllService categoryReadAllService;

    @MockBean
    private CategoryRepository categoryRepository;

    /**
     * Method under test: {@link CategoryReadAllService#getAllCategory()}
     */
    @Test
    void testGetAllCategory() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<CategoryResponse> actualAllCategory = categoryReadAllService.getAllCategory();

        // Assert
        verify(categoryRepository).findAll();
        assertTrue(actualAllCategory.isEmpty());
    }
}
