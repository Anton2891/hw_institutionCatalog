package com.example.hw_institutionCatalog.repository;

import com.example.hw_institutionCatalog.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Page<Review>> findAllById(Integer id, Pageable pageable);
    List<Review> findAllByInstitutionId(Integer institutionId);

}
