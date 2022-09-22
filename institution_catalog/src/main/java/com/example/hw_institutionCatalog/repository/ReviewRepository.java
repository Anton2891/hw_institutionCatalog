package com.example.hw_institutionCatalog.repository;

import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.repository.data.ReviewsList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Page<Review>> findAllById(Integer id, Pageable pageable);
    List<Review> findAllByInstitutionId(Integer institutionId);

    @Query(value = "select r.institution_id, left (review, 3), (character_length(review) > 3) as full_text, r.rating " +
            "from Review r where r.institution_id = :id", nativeQuery = true)
    Optional<List<ReviewsList>> findSmallReviewsList(@Param("id") Integer id);
}
