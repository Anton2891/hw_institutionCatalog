package com.example.hw_institutionCatalog.dto.out;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ReviewOutDto {
    private Integer id;
    private Integer institutionId;
    private Integer rating;
    private String review;

}
