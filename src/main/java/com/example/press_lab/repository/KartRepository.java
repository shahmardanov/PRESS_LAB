package com.example.press_lab.repository;

import com.example.press_lab.entity.Kart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KartRepository extends JpaRepository<Kart, Long> {
    Optional<Kart> findByContent(String content);
    List<Kart> findByFkNewsId(Long fkNewsId);

}
