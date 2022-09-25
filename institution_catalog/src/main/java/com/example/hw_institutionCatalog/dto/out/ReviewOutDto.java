package com.example.hw_institutionCatalog.dto.out;

import com.example.hw_institutionCatalog.entity.Institution;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "dto for display review")
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ReviewOutDto {
    private Integer id;
    private Institution institution;
    private Integer rating;
    private String review;

}
