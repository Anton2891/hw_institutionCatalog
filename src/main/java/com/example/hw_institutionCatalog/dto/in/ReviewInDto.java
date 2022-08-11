package com.example.hw_institutionCatalog.dto.in;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ReviewInDto {
    private Integer id;
    private Integer institutionId;
    private Integer rating;
    private String review;
}
