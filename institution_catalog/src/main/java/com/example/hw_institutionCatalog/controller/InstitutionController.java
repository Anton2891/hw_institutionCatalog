package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.mapper.InstitutionMapper;
import com.example.hw_institutionCatalog.mapper.ReviewMapper;
import com.example.hw_institutionCatalog.service.InstitutionService;
import com.example.hw_institutionCatalog.service.ReviewService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inst")
public class InstitutionController {
    private final InstitutionService institutionService;
    private final ReviewService reviewService;
    private final InstitutionMapper institutionMapper;
    private final ReviewMapper reviewMapper;

    private final InstitutionMapper mapper;

    public InstitutionController(InstitutionService service, ReviewService reviewService, InstitutionMapper institutionMapper, ReviewMapper reviewMapper, InstitutionMapper mapper) {
        this.institutionService = service;
        this.reviewService = reviewService;
        this.institutionMapper = institutionMapper;
        this.reviewMapper = reviewMapper;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<InstitutionOutDto> getAllInstitutions(@PageableDefault(sort = "name") Pageable pageable) {
        Page<Institution> institutions = institutionService.getAll(pageable);
        return institutions.map(institutionMapper::mapInstitutionToInstitutionOutDto);
    }

    @GetMapping("/{id}")
    public InstitutionOutDto getDescriptionInstitutionById(@PathVariable("id") Integer id)
            throws InstitutionNotFoundException {
        Institution institutionDesk = institutionService.getDescriptionInstitutionById(id);
        return mapper.mapInstitutionToInstitutionOutDto(institutionDesk);
    }

    @PostMapping
    public InstitutionOutDto createInstitution(@Valid @RequestBody InstitutionInDto institutionInDto) throws NumberParseException, FoundationDateIsExpiredException {
        Institution institution = institutionService.addInstitution(institutionInDto);
        return mapper.mapInstitutionToInstitutionOutDto(institution);
    }

    @PutMapping("/{id}/description")
    @ResponseStatus(HttpStatus.CREATED)
    public void refactorInstitutionById(@RequestParam (value = "description") String description,
                                        @PathVariable Integer id) throws InstitutionNotFoundException {
        institutionService.refactorInstitutionById(id, description);

    }

    @GetMapping("/{id}/reviews")
    public Page<ReviewOutDto> getReviewInstitutionById(@PageableDefault(sort = "name")
                                                       @PathVariable("id") Integer id, Pageable pageable)
            throws InstitutionNotFoundException {
        Page<Review> reviews = reviewService.getReviewInstitutionById(id, pageable);
        return reviews.map(reviewMapper::mapReviewToReviewOutDto);
    }

    @GetMapping("/{id}/rating")
    public Double getRatingInstitutionById(@PathVariable("id") Integer id,
                                                       @PageableDefault(sort = "name") Pageable pageable)
            throws InstitutionNotFoundException {
        Page<Review> reviews = reviewService.getRatingInstitutionById(id, pageable);
        return 0.5;
    }

    @PostMapping("/{id}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReview(@PathVariable Integer id,
                          @RequestParam(value = "rating") Integer rating,
                          @RequestParam(value = "review") String review) throws InstitutionNotFoundException {
        reviewService.addReview(id, rating, review);
//        service.addReview(review.getInstitutionId(), review.getRating(), review.getReview());
    }


}
