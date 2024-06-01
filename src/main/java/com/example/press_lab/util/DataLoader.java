package com.example.press_lab.util;

import com.example.press_lab.entity.Category;
import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder().name("Xəbərlər").build());
            categories.add(Category.builder().name("Dünya").build());
            categories.add(Category.builder().name("İdman").build());
            categories.add(Category.builder().name("İqtisadiyyat").build());
            categories.add(Category.builder().name("Siyasət").build());
            categories.add(Category.builder().name("Analitik").build());
            categories.add(Category.builder().name("Digər").build());
            categoryRepository.saveAll(categories);
            }
        if (subCategoryRepository.count() == 0) {
            List<SubCategory> subCategories = new ArrayList<>();
            subCategories.add(SubCategory.builder().name("Əsas Xəbərlər").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Ən Son Xəbərlər").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Ən Çox Oxunan Xəbərlər").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Eksklüziv Xəbərlər").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Bölgə Xəbərləri").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Dünya Xəbərləri").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Avropa").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Asiya").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Amerika").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Afrika").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Avstraliya").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("İdman Xəbərləri").fkCategoryId(3L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").fkCategoryId(3L).build());
            subCategories.add(SubCategory.builder().name("Dünya").fkCategoryId(3L).build());
            subCategories.add(SubCategory.builder().name("İqtisadiyyat Xəbərləri").fkCategoryId(4L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").fkCategoryId(4L).build());
            subCategories.add(SubCategory.builder().name("Dünya").fkCategoryId(4L).build());
            subCategories.add(SubCategory.builder().name("Siyasət Xəbərləri").fkCategoryId(5L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").fkCategoryId(5L).build());
            subCategories.add(SubCategory.builder().name("Dünya").fkCategoryId(5L).build());
            subCategories.add(SubCategory.builder().name("Analitik Xəbərləri").fkCategoryId(6L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").fkCategoryId(6L).build());
            subCategories.add(SubCategory.builder().name("Dünya").fkCategoryId(6L).build());
            subCategories.add(SubCategory.builder().name("Mədəniyyət").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Sağlamliq").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Təhsil").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Cəmiyyət").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Bloq").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Təqvim").fkCategoryId(7L).build());
            subCategoryRepository.saveAll(subCategories);
        }
    }

}
