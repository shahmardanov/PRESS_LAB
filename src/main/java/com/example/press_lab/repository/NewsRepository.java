package com.example.press_lab.repository;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByContent(String content);
    Optional<News> findByStatus(NewsStatus status);
}
