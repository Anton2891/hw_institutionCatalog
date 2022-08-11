package com.example.hw_institutionCatalog.service.impl;

import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.repository.InstitutionRepository;
import com.example.hw_institutionCatalog.repository.ReviewRepository;
import com.example.hw_institutionCatalog.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final InstitutionRepository institutionRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, InstitutionRepository institutionRepository) {
        this.reviewRepository = reviewRepository;
        this.institutionRepository = institutionRepository;
    }


    @Override
    public List<Review> getReviewInstitutionById(Integer id) throws InstitutionNotFoundException {
        Optional<List<Review>> byId = reviewRepository.findAllById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        throw new InstitutionNotFoundException(id);
    }

    @Override
    public List<Review> getRatingInstitutionById(Integer id) throws InstitutionNotFoundException{
        Optional<List<Review>> byId = reviewRepository.findAllById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        throw new InstitutionNotFoundException(id);
    }

    @Override
    public void addReview(Integer institutionId, Integer rating, String review) throws InstitutionNotFoundException {
//        Optional<Institution> byId = institutionRepository.findById(institutionId);
//        if (byId.isPresent()){
//            Institution institution = byId.get();
//            Review review1 = Review.builder()
//                    .institutionId(institutionId)
//                    .rating(rating)
//                    .review(review)
//                    .build();
//            reviewRepository.save(review1);
//        }
//        throw new InstitutionNotFoundException(institutionId);
        Review review1 = Review.builder()
                .institutionId(institutionId)
                .rating(rating)
                .review(review).build();
        reviewRepository.save(review1);
    }

    @Override
    public void refactorReviewById(Integer id, String review) throws InstitutionNotFoundException {
        Optional<Review> byId = reviewRepository.findById(id);
        if (byId.isPresent()){
            Review review1 = byId.get();
            review1.setReview(review);
            reviewRepository.save(review1);
        } else {
            throw new InstitutionNotFoundException(id);
        }
    }
}
