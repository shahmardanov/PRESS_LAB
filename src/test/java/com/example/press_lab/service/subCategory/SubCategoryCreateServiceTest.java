package com.example.press_lab.service.subCategory;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.request.subCategory.SubCategoryRequest;
import com.example.press_lab.response.subCategory.SubCategoryResponse;
import com.example.press_lab.service.util.SubCategoryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubCategoryCreateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubCategoryCreateServiceTest {
    @Autowired
    private SubCategoryCreateService subCategoryCreateService;

    @MockBean
    private SubCategoryMapper subCategoryMapper;

    @MockBean
    private SubCategoryRepository subCategoryRepository;

    /**
     * Method under test:
     * {@link SubCategoryCreateService#createCategory(SubCategoryRequest)}
     */
    @Test
    void testCreateCategory() {
        // Arrange
        SubCategory subCategory = SubCategoryUtil.subCategory();
        when(subCategoryRepository.save(Mockito.<SubCategory>any())).thenReturn(subCategory);

        SubCategory subCategory2 = SubCategoryUtil.subCategory();
        when(subCategoryMapper.mapRequestToEntity(Mockito.<SubCategoryRequest>any())).thenReturn(subCategory2);
        SubCategoryResponse buildResult = SubCategoryResponse.builder().fkCategoryId(1L).id(1L).name("Name").build();
        when(subCategoryMapper.mapSubCategoryToResponse(Mockito.<SubCategory>any())).thenReturn(buildResult);

        // Act
        subCategoryCreateService.createCategory(new SubCategoryRequest());

        // Assert
        verify(subCategoryMapper).mapRequestToEntity(isA(SubCategoryRequest.class));
        verify(subCategoryMapper).mapSubCategoryToResponse(isA(SubCategory.class));
        verify(subCategoryRepository).save(isA(SubCategory.class));
    }
}
