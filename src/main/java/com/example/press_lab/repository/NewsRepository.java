package com.example.press_lab.repository;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByContent(String content);

    Page<News> findByStatus(NewsStatus status, Pageable pageable);

    Page<News> findByStatusAndCategoryStatus(NewsStatus status, CategoryStatus categoryStatus, Pageable pageable);

    Page<News> findByStatusAndSubCategoryStatus(NewsStatus status, SubCategoryStatus subCategoryStatus, Pageable pageable);

    Page<News> findByStatusAndCategoryStatusAndSubCategoryStatus(NewsStatus status, CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus, Pageable pageable);

    Optional<News> findByStatusAndCategoryStatusAndSubCategoryStatus(NewsStatus status, CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus);

    @Query("SELECT n.categoryStatus, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.categoryStatus " +
            "ORDER BY totalViewCount DESC")
    List<CategoryStatus> findMostViewedCategoryStatus(Pageable pageable);


    @Query("SELECT n.categoryStatus, n.subCategoryStatus, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.categoryStatus, n.subCategoryStatus " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMostViewedSubCategoryStatus();

    @Query("SELECT n.subCategoryStatus, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "WHERE n.categoryStatus = :categoryStatus " +
            "GROUP BY n.subCategoryStatus " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMostViewedSubCategoryStatusFromCategory(@Param("categoryStatus") CategoryStatus categoryStatus);

    @Query("SELECT n.categoryStatus, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.categoryStatus " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMost5ViewedCategoryStatus(Pageable pageable);

    @Query("SELECT n.categoryStatus, n.subCategoryStatus, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.categoryStatus, n.subCategoryStatus " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMost5ViewedSubCategoryStatus(Pageable pageable);



}
