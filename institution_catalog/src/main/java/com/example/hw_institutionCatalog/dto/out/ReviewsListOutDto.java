package com.example.hw_institutionCatalog.dto.out;

import lombok.Data;

@Data
public class ReviewsListOutDto {
    private Integer institutionId;
    private String review;
    private Boolean fullText;
    private Integer rating;
}
