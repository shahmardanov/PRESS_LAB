package com.example.press_lab.service.category;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Category;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.repository.CategoryRepository;

import java.util.Optional;

import com.example.press_lab.service.util.CategoryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryDeleteService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CategoryDeleteServiceTest {
    @Autowired
    private CategoryDeleteService categoryDeleteService;

    @MockBean
    private CategoryRepository categoryRepository;

    /**
     * Method under test: {@link CategoryDeleteService#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() {
        // Arrange
        Category category =  CategoryUtil.category();
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepository).delete(Mockito.<Category>any());
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        categoryDeleteService.deleteCategory(1L);

        // Assert that nothing has changed
        verify(categoryRepository).delete(isA(Category.class));
        verify(categoryRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link CategoryDeleteService#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory2() {
        // Arrange
        Category category =  CategoryUtil.category();
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new SubCategoryNotFoundException()).when(categoryRepository).delete(Mockito.<Category>any());
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(SubCategoryNotFoundException.class, () -> categoryDeleteService.deleteCategory(1L));
        verify(categoryRepository).delete(isA(Category.class));
        verify(categoryRepository).findById(eq(1L));
    }
}
