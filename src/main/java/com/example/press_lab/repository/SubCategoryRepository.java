package com.example.press_lab.repository;

import com.example.press_lab.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    Optional<SubCategory> findByName(String name);

    List<SubCategory> findByFkCategoryId(Long fkCategoryId);

}
