package com.example.press_lab.service.subCategory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.repository.SubCategoryRepository;

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

@ContextConfiguration(classes = {SubCategoryDeleteService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubCategoryDeleteServiceTest {
    @Autowired
    private SubCategoryDeleteService subCategoryDeleteService;

    @MockBean
    private SubCategoryRepository subCategoryRepository;

    /**
     * Method under test: {@link SubCategoryDeleteService#deleteSubCategory(Long)}
     */
    @Test
    void testDeleteSubCategory() {
        // Arrange
        SubCategory subCategory = SubCategoryUtil.subCategory();
        Optional<SubCategory> ofResult = Optional.of(subCategory);
        doNothing().when(subCategoryRepository).delete(Mockito.<SubCategory>any());
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        subCategoryDeleteService.deleteSubCategory(1L);

        // Assert that nothing has changed
        verify(subCategoryRepository).delete(isA(SubCategory.class));
        verify(subCategoryRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link SubCategoryDeleteService#deleteSubCategory(Long)}
     */
    @Test
    void testDeleteSubCategory2() {
        // Arrange
        SubCategory subCategory = SubCategoryUtil.subCategory();
        Optional<SubCategory> ofResult = Optional.of(subCategory);
        doThrow(new SubCategoryNotFoundException()).when(subCategoryRepository).delete(Mockito.<SubCategory>any());
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(SubCategoryNotFoundException.class, () -> subCategoryDeleteService.deleteSubCategory(1L));
        verify(subCategoryRepository).delete(isA(SubCategory.class));
        verify(subCategoryRepository).findById(eq(1L));
    }
}
