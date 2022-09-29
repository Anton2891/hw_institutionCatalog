package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.dto.out.ReviewsListOutDto;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.exeption.ReviewNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    void refactorReviewById(Integer id, String review, Integer rating) throws ReviewNotFoundException;
    void deleteReviewById(Integer id);

    String getReviewById(Integer id) throws ReviewNotFoundException;
}
