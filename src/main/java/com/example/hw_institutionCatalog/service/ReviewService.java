package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewInstitutionById(Integer id) throws InstitutionNotFoundException;
    List<Review> getRatingInstitutionById(Integer id) throws InstitutionNotFoundException;
    Review addReview(Integer institutionId, Integer rating, String review) throws InstitutionNotFoundException;
    void refactorReviewById(Integer institutionId, String review) throws InstitutionNotFoundException;
}
