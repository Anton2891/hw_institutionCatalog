package com.example.hw_institutionCatalog.mapper;

import com.example.hw_institutionCatalog.dto.in.ReviewInDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.dto.out.ReviewsListOutDto;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.repository.data.ReviewsList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewOutDto mapReviewToReviewOutDto(Review review);

    Review mapReviewInDtoToReview(ReviewInDto reviewInDto);

    List<ReviewsListOutDto> mapReviewsListToReviewsListOutDto(List<ReviewsList> reviewsList);
}
