package com.example.hw_institutionCatalog.dto.in;


import com.example.hw_institutionCatalog.entity.Institution;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(description = "dto for create review")
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ReviewInDto {
//    private Integer id;
    @NotNull
    private Institution institution;
    private Integer rating;
    private String review;
}
