package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.out.ReviewsListOutDto;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.exeption.ReviewNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/review")
public interface ReviewController {

    @Operation(summary = "Refactor review by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "review is update"),
            @ApiResponse(
                    responseCode = "404",
                    description = "review is not found"
            )
    })
    @PutMapping("/{id}")
    void refactorReviewById(@RequestParam(value = "review") String review,
                            @RequestParam(value = "rating") Integer rating,
                            @PathVariable Integer id) throws ReviewNotFoundException;

    @GetMapping("/{id}")
    String getReviewById(@PathVariable Integer id) throws ReviewNotFoundException;

    @Operation(summary = "Delete review by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "review is delete"),
            @ApiResponse(
                    responseCode = "404",
                    description = "review is not found"
            )
    })
    @DeleteMapping("/{id}")
    void deleteReviewById(@PathVariable Integer id);
}
