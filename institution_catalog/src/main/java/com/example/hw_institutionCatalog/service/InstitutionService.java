package com.example.hw_institutionCatalog.service;


import com.example.hw_institutionCatalog.dto.in.AddOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InstitutionService {
    Page<Institution> getAll(Pageable pageable);
    Institution getDescriptionInstitutionById(Integer id) throws InstitutionNotFoundException;
    Institution addInstitution(InstitutionInDto institutionInDto/*String name, String address, String description,LocalDate foundationDate*/) throws FoundationDateIsExpiredException, NumberParseException;
    LocalDate getFoundationDate(Integer id) throws InstitutionNotFoundException;
    void refactorInstitutionById(Integer id, String description) throws InstitutionNotFoundException;
    Page<Review> getReviewInstitutionById(Integer id, Pageable pageable) throws InstitutionNotFoundException;
    Page<Review> getRatingInstitutionById(Integer id, Pageable pageable) throws InstitutionNotFoundException;
    Review addReview(Integer institutionId, Integer rating, String review) throws InstitutionNotFoundException;
    void deleteOwner(DeleteOwnerInDto deleteOwnerInDto) throws InstitutionNotFoundException;
    List<Institution> getInstitutionByOwnerId(Integer ownerId) throws InstitutionNotFoundException;

    void addOwner(AddOwnerInDto addOwnerInDto) throws InstitutionNotFoundException;

    void changeOwner(ChangeOwnerInDto changeOwnerInDto) throws InstitutionNotFoundException;
}
