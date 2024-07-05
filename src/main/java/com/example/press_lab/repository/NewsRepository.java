package com.example.press_lab.repository;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByIdAndStatus(Long id, NewsStatus status);

    Optional<News> findByContent(String content);

    List<News> findByStatus(NewsStatus status);

    Page<News> findByStatusAndFkCategoryId(NewsStatus status, Long fkCategoryId, Pageable pageable);

    Page<News> findByStatusAndFkSubCategoryId(NewsStatus status, Long fkSubCategoryId, Pageable pageable);

    Page<News> findByStatusAndFkCategoryIdAndFkSubCategoryId(NewsStatus status, Long fkCategoryId, Long fkSubCategoryId, Pageable pageable);
    
    Page<News> findByCreatedAtAfter(LocalDateTime dateTime, Pageable pageable);

    @Query("SELECT n.fkCategoryId, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.fkCategoryId " +
            "ORDER BY totalViewCount DESC")
    List<Long> findMostViewedCategoryStatus(Pageable pageable);


    @Query("SELECT n.fkCategoryId, n.fkSubCategoryId, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.fkCategoryId, n.fkSubCategoryId " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMostViewedSubCategoryStatus();

    @Query("SELECT n.fkSubCategoryId, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "WHERE n.fkCategoryId = :fkCategoryId " +
            "GROUP BY n.fkSubCategoryId " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMostViewedSubCategoryStatusFromCategory(@Param("fkCategoryId") Long fkCategoryId);

    @Query("SELECT n.fkCategoryId, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.fkCategoryId " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMost5ViewedCategoryStatus(Pageable pageable);

    @Query("SELECT n.fkCategoryId, n.fkSubCategoryId, SUM(n.viewCount) AS totalViewCount " +
            "FROM News n " +
            "GROUP BY n.fkCategoryId, n.fkSubCategoryId " +
            "ORDER BY totalViewCount DESC")
    List<Object[]> findMost5ViewedSubCategoryStatus(Pageable pageable);

    List<News> findByOrderByViewCountDesc(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE News n SET n.viewCount = n.viewCount + 1 WHERE n.id = :id")
    void incrementViewCount(Long id);

}
