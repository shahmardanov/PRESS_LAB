package com.example.press_lab.repository;

import com.example.press_lab.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Optional<Advertisement> findByContent(String Content);
    Optional<Advertisement> findBySourceUrl(String sourceUrl);
}
