package com.example.hw_institutionCatalog.service.impl;

import com.example.hw_institutionCatalog.dto.out.ReviewsListOutDto;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.exeption.ReviewNotFoundException;
import com.example.hw_institutionCatalog.mapper.ReviewMapper;
import com.example.hw_institutionCatalog.repository.ReviewRepository;
import com.example.hw_institutionCatalog.repository.data.ReviewsList;
import com.example.hw_institutionCatalog.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void refactorReviewById(Integer id, String review, Integer rating) throws ReviewNotFoundException {
        Optional<Review> byId = reviewRepository.findById(id);
        if (byId.isPresent()){
            Review review1 = byId.get();
            review1.setReview(review);
            review1.setRating(rating);
            reviewRepository.save(review1);
        } else {
            throw new ReviewNotFoundException(id);
        }
    }

    @Override
    public String getReviewById(Integer id) throws ReviewNotFoundException {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty()){
            throw new ReviewNotFoundException(id);
        }
        return review.get().getReview();
    }

    @Override
    @Transactional
    public List<ReviewsListOutDto> getReviewsByInstitutionId(Integer institutionId) throws InstitutionNotFoundException {
        List<ReviewsList> byId = reviewRepository.findSmallReviewsList(institutionId);
//        if(byId.isEmpty()){
//            throw new InstitutionNotFoundException(institutionId);
//        }
//        List<ReviewsList> reviews = byId.get();
//        System.out.println(reviews);
//        List<ReviewsListOutDto> reviewsOut = new ArrayList<>();
        ;
//        System.out.println(byId.toString());
//        for (ReviewsList review : byId) {
//            reviewsOut.add(reviewMapper.mapReviewsListToReviewsListOutDto(review));
//        }
//        return byId.stream().map(reviewMapper::mapReviewsListToReviewsListOutDto).toList();
        return reviewMapper.mapReviewsListToReviewsListOutDto(byId);
    }

    @Override
    public void deleteReviewById(Integer id) {
        reviewRepository.deleteById(id);
    }


}
