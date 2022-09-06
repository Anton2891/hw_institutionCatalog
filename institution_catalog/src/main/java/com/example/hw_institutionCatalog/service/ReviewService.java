package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> getReviewInstitutionById(Integer id, Pageable pageable) throws InstitutionNotFoundException;
    Page<Review> getRatingInstitutionById(Integer id, Pageable pageable) throws InstitutionNotFoundException;
    Review addReview(Integer institutionId, Integer rating, String review) throws InstitutionNotFoundException;
    void refactorReviewById(Integer institutionId, String review) throws InstitutionNotFoundException;

    void deleteReviewById(Integer id);
}
