package com.example.press_lab.service.subCategory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.mappers.SubCategoryMapper;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.request.subCategory.SubCategoryRequest;
import com.example.press_lab.response.subCategory.SubCategoryResponse;

import java.util.Optional;

import com.example.press_lab.service.util.SubCategoryUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubCategoryUpdateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubCategoryUpdateServiceTest {
    @MockBean
    private SubCategoryMapper subCategoryMapper;

    @MockBean
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryUpdateService subCategoryUpdateService;

    /**
     * Method under test:
     * {@link SubCategoryUpdateService#updateCategory(Long, SubCategoryRequest)}
     */
    @Test
    void testUpdateCategory() {
        // Arrange
        SubCategory subCategory = SubCategoryUtil.subCategory();
        Optional<SubCategory> ofResult = Optional.of(subCategory);

        SubCategory subCategory2 = SubCategoryUtil.subCategory();
        when(subCategoryRepository.save(Mockito.<SubCategory>any())).thenReturn(subCategory2);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        SubCategory subCategory3 = SubCategoryUtil.subCategory();
        when(subCategoryMapper.updateSubCategory(Mockito.<SubCategoryRequest>any(), Mockito.<SubCategory>any()))
                .thenReturn(subCategory3);
        SubCategoryResponse buildResult = SubCategoryResponse.builder().fkCategoryId(1L).id(1L).name("Name").build();
        when(subCategoryMapper.mapSubCategoryToResponse(Mockito.<SubCategory>any())).thenReturn(buildResult);

        // Act
        subCategoryUpdateService.updateCategory(1L, new SubCategoryRequest());

        // Assert
        verify(subCategoryMapper).mapSubCategoryToResponse(isA(SubCategory.class));
        verify(subCategoryMapper).updateSubCategory(isA(SubCategoryRequest.class), isA(SubCategory.class));
        verify(subCategoryRepository).findById(eq(1L));
        verify(subCategoryRepository).save(isA(SubCategory.class));
    }

    /**
     * Method under test:
     * {@link SubCategoryUpdateService#updateCategory(Long, SubCategoryRequest)}
     */
    @Test
    void testUpdateCategory2() {
        // Arrange
        SubCategory subCategory = SubCategoryUtil.subCategory();
        Optional<SubCategory> ofResult = Optional.of(subCategory);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(subCategoryMapper.updateSubCategory(Mockito.<SubCategoryRequest>any(), Mockito.<SubCategory>any()))
                .thenThrow(new SubCategoryNotFoundException());

        // Act and Assert
        assertThrows(SubCategoryNotFoundException.class,
                () -> subCategoryUpdateService.updateCategory(1L, new SubCategoryRequest()));
        verify(subCategoryMapper).updateSubCategory(isA(SubCategoryRequest.class), isA(SubCategory.class));
        verify(subCategoryRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link SubCategoryUpdateService#updateCategory(Long, SubCategoryRequest)}
     */
    @Test
    void testUpdateCategory3() {
        // Arrange
        SubCategory subCategory = SubCategoryUtil.subCategory();
        Optional<SubCategory> ofResult = Optional.of(subCategory);
        when(subCategoryRepository.save(Mockito.<SubCategory>any())).thenThrow(new SubCategoryNotFoundException());
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        SubCategory subCategory2 =SubCategoryUtil.subCategory();
        when(subCategoryMapper.updateSubCategory(Mockito.<SubCategoryRequest>any(), Mockito.<SubCategory>any()))
                .thenReturn(subCategory2);
        SubCategoryRequest categoryRequest = SubCategoryRequest.builder().fkCategoryId(1L).name("Name").build();

        // Act and Assert
        assertThrows(SubCategoryNotFoundException.class,
                () -> subCategoryUpdateService.updateCategory(1L, categoryRequest));
        verify(subCategoryMapper).updateSubCategory(isA(SubCategoryRequest.class), isA(SubCategory.class));
        verify(subCategoryRepository).findById(eq(1L));
        verify(subCategoryRepository).save(isA(SubCategory.class));
    }
}
