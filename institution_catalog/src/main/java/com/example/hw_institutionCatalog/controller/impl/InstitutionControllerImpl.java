package com.example.hw_institutionCatalog.controller.impl;

import com.example.hw_institutionCatalog.controller.InstitutionController;
import com.example.hw_institutionCatalog.dto.in.AddOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionSmallOutDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.exeption.OwnerNotFoundException;
import com.example.hw_institutionCatalog.mapper.InstitutionMapper;
import com.example.hw_institutionCatalog.mapper.ReviewMapper;
import com.example.hw_institutionCatalog.service.InstitutionService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    public InstitutionOutDto getInstitutionById(Integer id)
            throws InstitutionNotFoundException {
        Institution institutionDesk = institutionService.getInstitutionById(id);
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

    @Override
    public BigDecimal getRatingInstitutionById(Integer id) throws InstitutionNotFoundException {
        return institutionService.getRatingInstitutionById(id);
    }

    @Override
    public void addReview(Integer id, Integer rating, String review) throws InstitutionNotFoundException {
        institutionService.addReview(id, rating, review);
    }

    @Override
    public void deleteOwner(DeleteOwnerInDto deleteOwnerInDto) throws InstitutionNotFoundException {
        institutionService.deleteOwner(deleteOwnerInDto);
    }

    @Override
    public void addOwner(AddOwnerInDto addOwnerInDto) throws InstitutionNotFoundException {
        institutionService.addOwner(addOwnerInDto);
    }

    @Override
    public void changeOwner(ChangeOwnerInDto changeOwnerInDto) throws InstitutionNotFoundException, OwnerNotFoundException {
        institutionService.changeOwner(changeOwnerInDto);
    }

    @Override
    public List<InstitutionSmallOutDto> getSmallList() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.by("average")));
        return institutionService.getSmallList(pageable);
    }

    @Override
    public void deleteInstitutionById(Integer id) throws InstitutionNotFoundException{
        institutionService.deleteInstitutionById(id);
    }

}
