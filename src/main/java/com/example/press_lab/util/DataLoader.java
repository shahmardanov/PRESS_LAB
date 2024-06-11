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
            categories.add(Category.builder().name("Xəbərlər").nameRu("Новости").nameEn("News").build());
            categories.add(Category.builder().name("Dünya").nameRu("Мир").nameEn("World").build());
            categories.add(Category.builder().name("İdman").nameRu("Виды спорта").nameEn("Sports").build());
            categories.add(Category.builder().name("İqtisadiyyat").nameRu("Экономика").nameEn("Economy").build());
            categories.add(Category.builder().name("Siyasət").nameRu("Политика").nameEn("Policy").build());
            categories.add(Category.builder().name("Analitik").nameRu("Аналитик").nameEn("Analyst").build());
            categories.add(Category.builder().name("Digər").nameRu("Другой").nameEn("Another").build());
            categoryRepository.saveAll(categories);
            }
        if (subCategoryRepository.count() == 0) {
            List<SubCategory> subCategories = new ArrayList<>();
            subCategories.add(SubCategory.builder().name("Əsas Xəbərlər").nameRu("Главные новости").nameEn("Main News").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Ən Son Xəbərlər").nameRu("Последние новости").nameEn("Last News").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Ən Çox Oxunan Xəbərlər").nameRu("Самые читаемые новости").nameEn("Most Read News").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Eksklüziv Xəbərlər").nameRu("Эксклюзивные новости").nameEn("Exclusive News").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Bölgə Xəbərləri").nameRu("Региональные новости").nameEn("Regional News").fkCategoryId(1L).build());
            subCategories.add(SubCategory.builder().name("Dünya Xəbərləri").nameRu("Мировые новости").nameEn("World News").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Avropa").nameRu("Европа").nameEn("Europe").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Asiya").nameRu("Азия").nameEn("Asia").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Amerika").nameRu("Америка").nameEn("America").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Afrika").nameRu("Африка").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("Avstraliya").nameRu("Австралия").nameEn("Africa").fkCategoryId(2L).build());
            subCategories.add(SubCategory.builder().name("İdman Xəbərləri").nameRu("Спортивные новости").nameEn("Sports News").fkCategoryId(3L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").nameRu("Страна").nameEn("Country").fkCategoryId(3L).build());
            subCategories.add(SubCategory.builder().name("Dünya").nameRu("Мир").nameEn("World").fkCategoryId(3L).build());
            subCategories.add(SubCategory.builder().name("İqtisadiyyat Xəbərləri").nameRu("Новости экономики").nameEn("Economic News").fkCategoryId(4L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").nameRu("Страна").nameEn("Country").fkCategoryId(4L).build());
            subCategories.add(SubCategory.builder().name("Dünya").nameRu("Мир").nameEn("World").fkCategoryId(4L).build());
            subCategories.add(SubCategory.builder().name("Siyasət Xəbərləri").nameRu("Новости политики").nameEn("Politics News").fkCategoryId(5L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").nameRu("Страна").nameEn("Country").fkCategoryId(5L).build());
            subCategories.add(SubCategory.builder().name("Dünya").nameRu("Мир").nameEn("World").fkCategoryId(5L).build());
            subCategories.add(SubCategory.builder().name("Analitik Xəbərləri").nameRu("Аналитические новости").nameEn("Analytical News").fkCategoryId(6L).build());
            subCategories.add(SubCategory.builder().name("Ölkə").nameRu("Страна").nameEn("Country").fkCategoryId(6L).build());
            subCategories.add(SubCategory.builder().name("Dünya").nameRu("Мир").nameEn("World").fkCategoryId(6L).build());
            subCategories.add(SubCategory.builder().name("Mədəniyyət").nameRu("Культура").nameEn("Culture").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Sağlamliq").nameRu("Здоровье").nameEn("Health").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Təhsil").nameRu("Образование").nameEn("Education").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Cəmiyyət").nameRu("Общество").nameEn("Society").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Bloq").nameRu("Блог").nameEn("Blog").fkCategoryId(7L).build());
            subCategories.add(SubCategory.builder().name("Təqvim").nameRu("Календарь").nameEn("Calendar").fkCategoryId(7L).build());
            subCategoryRepository.saveAll(subCategories);
        }
    }

}
