package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.mapper.ReviewMapper;
import com.example.hw_institutionCatalog.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ReviewController {
    private final ReviewServiceImpl service;

    @Autowired
    private ReviewMapper mapper;

    public ReviewController(ReviewServiceImpl service) {
        this.service = service;
    }

    @GetMapping("rev/{id}")
    public List<ReviewOutDto> getReviewInstitutionById(@PathVariable("id") Integer id) throws InstitutionNotFoundException {
        List<Review> reviews = service.getReviewInstitutionById(id);
        List<ReviewOutDto> reviewOutDtoList = new ArrayList<>();
        for (Review r: reviews) {
            reviewOutDtoList.add(mapper.mapReviewToReviewOutDto(r));
            }
        return reviewOutDtoList;
    }

    @GetMapping("rat/{id}")
    public List<ReviewOutDto> getRatingInstitutionById(@PathVariable("id") Integer id) throws InstitutionNotFoundException {
        List<Review> reviews = service.getRatingInstitutionById(id);
        List<ReviewOutDto> reviewOutDtoList = new ArrayList<>();
        for (Review r: reviews) {
            reviewOutDtoList.add(mapper.mapReviewToReviewOutDto(r));
        }
        return reviewOutDtoList;
    }

    @PostMapping("add/rev")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReview(@RequestParam (value = "institutionId") Integer institutionId,
            @RequestParam(value = "rating") Integer rating,
            @RequestParam(value = "review") String review) throws InstitutionNotFoundException {
        service.addReview(institutionId, rating, review);
//        service.addReview(review.getInstitutionId(), review.getRating(), review.getReview());
    }

    @PostMapping("ref/rev/{institutionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void refactorReviewById(@RequestParam(value = "review") String review,
                                   @PathVariable Integer institutionId) throws InstitutionNotFoundException {
        service.refactorReviewById(institutionId, review);
    }
}
