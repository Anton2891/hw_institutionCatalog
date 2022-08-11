package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewServiceTest extends AppContextTest {

    @Autowired
    private ReviewService reviewService;

    @BeforeAll
    void setUp() throws InstitutionNotFoundException {
        String RESTAURANT_WITH_REVIEW = "restaurant_with_review";
        reviewService.addReview(6, 15, RESTAURANT_WITH_REVIEW);
        String RESTAURANT_WITHOUT_REVIEW = "restaurant_without_review";
        reviewService.addReview(7, 5, RESTAURANT_WITHOUT_REVIEW);
    }

    @Test
    void addReview() throws InstitutionNotFoundException {
        String review = "review_test";
        Integer rating = 6;
        Integer id = 8;
        reviewService.addReview(id, rating, review);
    }

    @Test
    void getReviewInstitutionById() throws InstitutionNotFoundException {
        Integer id = 8;
        assertNotNull(reviewService.getReviewInstitutionById(id));
        System.out.println(reviewService.getReviewInstitutionById(id));
    }

    @Test
    void getRatingInstitutionById() throws InstitutionNotFoundException {
        Integer id = 8;
        assertNotNull(reviewService.getRatingInstitutionById(id));
    }

    @Test
    void refactorReviewById() throws InstitutionNotFoundException {
        Integer id = 6;
        var oldReviews = reviewService.getReviewInstitutionById(id);
        reviewService.refactorReviewById(id, "new_test_review");
        var newReviews = reviewService.getReviewInstitutionById(id);
        assertNotSame(oldReviews, newReviews);
    }

}
