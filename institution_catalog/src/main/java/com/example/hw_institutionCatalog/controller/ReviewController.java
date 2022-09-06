package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.mapper.ReviewMapper;
import com.example.hw_institutionCatalog.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewServiceImpl service;

    public ReviewController(ReviewServiceImpl service) {
        this.service = service;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void refactorReviewById(@RequestParam(value = "review") String review,
                                   @PathVariable Integer id) throws InstitutionNotFoundException {
        service.refactorReviewById(id, review);
    }

    @DeleteMapping("/{id}")
    public void deleteReviewById(@PathVariable Integer id){
        service.deleteReviewById(id);
    }



}
