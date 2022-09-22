package com.example.hw_institutionCatalog.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends Exception{
    private final Integer reviewId;

    public ReviewNotFoundException(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getReviewId() {
        return reviewId;
    }
}
