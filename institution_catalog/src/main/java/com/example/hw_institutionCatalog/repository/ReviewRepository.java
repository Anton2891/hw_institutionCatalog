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

    @Query(value = "select institution_id as institutionId, substr (review, 1, 100) as review, char_length(review) < 3 " +
            "as fullText, rating from review as r where institution_id = :id", nativeQuery = true)
    List<ReviewsList> findSmallReviewsList(@Param("id") Integer id);

}
