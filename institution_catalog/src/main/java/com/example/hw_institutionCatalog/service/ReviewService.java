package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    void refactorReviewById(Integer institutionId, String review) throws InstitutionNotFoundException;
    void deleteReviewById(Integer id);
}
