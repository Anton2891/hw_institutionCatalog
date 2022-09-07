package com.example.hw_institutionCatalog.controller.impl;

import com.example.hw_institutionCatalog.controller.InstitutionController;
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
public class InstitutionControllerImpl implements InstitutionController {
    private final InstitutionService institutionService;
    private final InstitutionMapper institutionMapper;
    private final ReviewMapper reviewMapper;

    private final InstitutionMapper mapper;

    public InstitutionControllerImpl(InstitutionService service, InstitutionMapper institutionMapper, ReviewMapper reviewMapper, InstitutionMapper mapper) {
        this.institutionService = service;
        this.institutionMapper = institutionMapper;
        this.reviewMapper = reviewMapper;
        this.mapper = mapper;
    }

    @Override
    public Page<InstitutionOutDto> getAllInstitutions(Pageable pageable) {
        Page<Institution> institutions = institutionService.getAll(pageable);
        return institutions.map(institutionMapper::mapInstitutionToInstitutionOutDto);
    }

    @Override
    public InstitutionOutDto getDescriptionInstitutionById(Integer id)
            throws InstitutionNotFoundException {
        Institution institutionDesk = institutionService.getDescriptionInstitutionById(id);
        return mapper.mapInstitutionToInstitutionOutDto(institutionDesk);
    }

    @Override
    public InstitutionOutDto createInstitution(InstitutionInDto institutionInDto) throws NumberParseException, FoundationDateIsExpiredException {
        Institution institution = institutionService.addInstitution(institutionInDto);
        return mapper.mapInstitutionToInstitutionOutDto(institution);
    }

    @Override
    public void refactorInstitutionById(String description, Integer id) throws InstitutionNotFoundException {
        institutionService.refactorInstitutionById(id, description);

    }

    @Override
    public Page<ReviewOutDto> getReviewInstitutionById(Integer id, Pageable pageable)
            throws InstitutionNotFoundException {
        Page<Review> reviews = institutionService.getReviewInstitutionById(id, pageable);
        return reviews.map(reviewMapper::mapReviewToReviewOutDto);
    }

    //надо реализовать
    @Override
    public Double getRatingInstitutionById(Integer id, Pageable pageable)
            throws InstitutionNotFoundException {
        Page<Review> reviews = institutionService.getRatingInstitutionById(id, pageable);
        return 0.5;
    }

    @Override
    public void addReview(Integer id, Integer rating, String review) throws InstitutionNotFoundException {
        institutionService.addReview(id, rating, review);
    }
}
