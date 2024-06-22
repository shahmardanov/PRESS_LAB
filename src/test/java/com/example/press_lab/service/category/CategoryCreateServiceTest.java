package com.example.press_lab.service.category;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Category;
import com.example.press_lab.mappers.CategoryMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.request.category.CategoryRequest;
import com.example.press_lab.response.category.CategoryResponse;
import com.example.press_lab.service.util.CategoryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryCreateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CategoryCreateServiceTest {
    @Autowired
    private CategoryCreateService categoryCreateService;

    @MockBean
    private CategoryMapper categoryMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    /**
     * Method under test:
     * {@link CategoryCreateService#createCategory(CategoryRequest)}
     */
    @Test
    void testCreateCategory() {
        // Arrange
        Category category = CategoryUtil.category();
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category);

        Category category2 =  CategoryUtil.category();
        when(categoryMapper.mapRequestToEntity(Mockito.<CategoryRequest>any())).thenReturn(category2);
        CategoryResponse buildResult = CategoryResponse.builder().id(1L).name("Name").build();
        when(categoryMapper.mapCategoryToResponse(Mockito.<Category>any())).thenReturn(buildResult);

        // Act
        categoryCreateService.createCategory(new CategoryRequest("Name"));

        // Assert
        verify(categoryMapper).mapCategoryToResponse(isA(Category.class));
        verify(categoryMapper).mapRequestToEntity(isA(CategoryRequest.class));
        verify(categoryRepository).save(isA(Category.class));
    }
}
