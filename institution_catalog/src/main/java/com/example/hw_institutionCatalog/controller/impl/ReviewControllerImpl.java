package com.example.hw_institutionCatalog.controller.impl;

import com.example.hw_institutionCatalog.controller.ReviewController;
import com.example.hw_institutionCatalog.dto.out.ReviewsListOutDto;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.exeption.ReviewNotFoundException;
import com.example.hw_institutionCatalog.service.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewControllerImpl implements ReviewController {
    private final ReviewServiceImpl service;

    public ReviewControllerImpl(ReviewServiceImpl service) {
        this.service = service;
    }


    public void refactorReviewById(String review, Integer rating, Integer id) throws ReviewNotFoundException {
        service.refactorReviewById(id, review, rating);
    }

    @Override
    public String getReviewById(Integer id) throws ReviewNotFoundException {
        String review = service.getReviewById(id);
        return review;
    }

    public void deleteReviewById(Integer id){
        service.deleteReviewById(id);
    }
}
