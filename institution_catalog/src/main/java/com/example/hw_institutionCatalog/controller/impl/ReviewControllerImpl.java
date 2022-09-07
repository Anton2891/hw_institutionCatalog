package com.example.hw_institutionCatalog.controller.impl;

import com.example.hw_institutionCatalog.controller.ReviewController;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.service.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewControllerImpl implements ReviewController {
    private final ReviewServiceImpl service;

    public ReviewControllerImpl(ReviewServiceImpl service) {
        this.service = service;
    }


    public void refactorReviewById(String review, Integer id) throws InstitutionNotFoundException {
        service.refactorReviewById(id, review);
    }

    public void deleteReviewById(Integer id){
        service.deleteReviewById(id);
    }
}
