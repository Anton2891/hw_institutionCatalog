package com.example.hw_institutionCatalog.mapper;

import com.example.hw_institutionCatalog.dto.in.ReviewInDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewOutDto mapReviewToReviewOutDto(Review review);

    Review mapReviewInDtoToReview(ReviewInDto reviewInDto);
}
