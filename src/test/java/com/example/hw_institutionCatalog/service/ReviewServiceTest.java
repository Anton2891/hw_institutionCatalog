package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewServiceTest extends AppContextTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private InstitutionService institutionService;
    private Institution institutionWithReview;
    private Institution institutionWithoutReview;

    @BeforeAll
    void setUp() throws FoundationDateIsExpiredException, NumberParseException {
        String RESTAURANT_WITH_REVIEW = "inst_with_rev";
        institutionWithReview = institutionService.addInstitution( InstitutionInDto.builder()
                .name(RESTAURANT_WITH_REVIEW)
                .address("test_address_with_review")
                .description("test_description_with_review")
                .foundationDate(LocalDate.of(2012, 1, 1))
                .telephoneNumber("+7-926-926-92-96")
                .build());
        String RESTAURANT_WITHOUT_REVIEW = "inst_without_rev";
        institutionWithoutReview = institutionService.addInstitution( InstitutionInDto.builder()
                .name(RESTAURANT_WITHOUT_REVIEW)
                .address("test_address_without_review")
                .description("test_description_without_review")
                .foundationDate(LocalDate.of(2011, 12, 31))
                .telephoneNumber("+79168521213")
                .build());
    }

    @Test
    void addReview() throws InstitutionNotFoundException {
        Review review = reviewService.addReview(institutionWithReview.getId(), 6, "test_review_text");
        Page<Review> reviewTexts = reviewService.getReviewInstitutionById(review.getId(), Pageable.unpaged());
        assertEquals(1, reviewTexts.getTotalElements());
        assertEquals("test_review_text", reviewTexts.toList().get(0).getReview());
        Assertions.assertThrows(InstitutionNotFoundException.class, () ->
                reviewService.addReview(500, 98, "qqq"));

    }

    @Test
    void getReviewInstitutionById() throws InstitutionNotFoundException {
        Integer id = 11;
        assertNotNull(reviewService.getReviewInstitutionById(id, Pageable.unpaged()));
        System.out.println(reviewService.getReviewInstitutionById(id, Pageable.unpaged()));
    }

    @Test
    void getRatingInstitutionById() throws InstitutionNotFoundException {
        Integer id = 8;
        assertNotNull(reviewService.getRatingInstitutionById(id, Pageable.unpaged()));
    }

    @Test
    void refactorReviewById() throws InstitutionNotFoundException {
        Integer id = 6;
        var oldReviews = reviewService.getReviewInstitutionById(id, Pageable.unpaged());
        reviewService.refactorReviewById(id, "new_test_review");
        var newReviews = reviewService.getReviewInstitutionById(id, Pageable.unpaged());
        assertNotSame(oldReviews, newReviews);
    }

}
