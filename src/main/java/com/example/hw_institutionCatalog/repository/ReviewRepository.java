package com.example.hw_institutionCatalog.repository;

import com.example.hw_institutionCatalog.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<List<Review>> findAllById(Integer id);
    List<Review> findAllByInstitutionId(Integer institutionId);

}
