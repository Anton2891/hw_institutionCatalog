package com.example.hw_institutionCatalog.dto.in;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ReviewInDto {
    private Integer id;
    @NotNull
    private Integer institutionId;
    private Integer rating;
    private String review;
}
