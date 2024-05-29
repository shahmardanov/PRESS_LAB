package com.example.press_lab.repository;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByContent(String content);

    Page<News> findByStatus(NewsStatus status, Pageable pageable);

    Page<News> findByStatusAndCategoryStatus(NewsStatus status, CategoryStatus categoryStatus, Pageable pageable);

    Page<News> findByStatusAndSubCategoryStatus(NewsStatus status, SubCategoryStatus subCategoryStatus, Pageable pageable);

    Page<News> findByStatusAndCategoryStatusAndSubCategoryStatus(NewsStatus status, CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus, Pageable pageable);

}
