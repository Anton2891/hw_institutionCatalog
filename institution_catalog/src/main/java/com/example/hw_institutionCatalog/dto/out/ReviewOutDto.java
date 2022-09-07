package com.example.hw_institutionCatalog.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "dto for display review")
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ReviewOutDto {
    private Integer id;
    private Integer institution;
    private Integer rating;
    private String review;

}
