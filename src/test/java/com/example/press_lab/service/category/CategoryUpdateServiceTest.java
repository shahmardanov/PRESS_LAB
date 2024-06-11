package com.example.press_lab.service.category;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Category;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;

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

@ContextConfiguration(classes = {CategoryUpdateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CategoryUpdateServiceTest {
    @MockBean
    private CategoryMapper categoryMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryUpdateService categoryUpdateService;

    /**
     * Method under test:
     * {@link CategoryUpdateService#updateCategory(Long, CategoryRequest)}
     */
    @Test
    void testUpdateCategory() {
        // Arrange
        Category category =  CategoryUtil.category();
        Optional<Category> ofResult = Optional.of(category);

        Category category2 =  CategoryUtil.category();
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category2);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category3 =  CategoryUtil.category();
        when(categoryMapper.updateCategoryToCategoryUpdateResponse(Mockito.<CategoryRequest>any(), Mockito.<Category>any()))
                .thenReturn(category3);
        CategoryResponse buildResult = CategoryResponse.builder().id(1L).name("Name").build();
        when(categoryMapper.mapCategoryToResponse(Mockito.<Category>any())).thenReturn(buildResult);

        // Act
        categoryUpdateService.updateCategory(1L, new CategoryRequest("Name"));

        // Assert
        verify(categoryMapper).mapCategoryToResponse(isA(Category.class));
        verify(categoryMapper).updateCategoryToCategoryUpdateResponse(isA(CategoryRequest.class), isA(Category.class));
        verify(categoryRepository).findById(eq(1L));
        verify(categoryRepository).save(isA(Category.class));
    }

    /**
     * Method under test:
     * {@link CategoryUpdateService#updateCategory(Long, CategoryRequest)}
     */
    @Test
    void testUpdateCategory2() {
        // Arrange
        Category category =  CategoryUtil.category();
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(categoryMapper.updateCategoryToCategoryUpdateResponse(Mockito.<CategoryRequest>any(), Mockito.<Category>any()))
                .thenThrow(new SubCategoryNotFoundException());

        // Act and Assert
        assertThrows(SubCategoryNotFoundException.class,
                () -> categoryUpdateService.updateCategory(1L, new CategoryRequest("Name")));
        verify(categoryMapper).updateCategoryToCategoryUpdateResponse(isA(CategoryRequest.class), isA(Category.class));
        verify(categoryRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link CategoryUpdateService#updateCategory(Long, CategoryRequest)}
     */
    @Test
    void testUpdateCategory3() {
        // Arrange
        Category category =  CategoryUtil.category();
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save(Mockito.<Category>any())).thenThrow(new SubCategoryNotFoundException());
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category2 =  CategoryUtil.category();
        when(categoryMapper.updateCategoryToCategoryUpdateResponse(Mockito.<CategoryRequest>any(), Mockito.<Category>any()))
                .thenReturn(category2);
        CategoryRequest categoryRequest = CategoryRequest.builder().name("Name").build();

        // Act and Assert
        assertThrows(SubCategoryNotFoundException.class, () -> categoryUpdateService.updateCategory(1L, categoryRequest));
        verify(categoryMapper).updateCategoryToCategoryUpdateResponse(isA(CategoryRequest.class), isA(Category.class));
        verify(categoryRepository).findById(eq(1L));
        verify(categoryRepository).save(isA(Category.class));
    }
}
