package com.example.hw_institutionCatalog.service;


import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface InstitutionService {
    Page<Institution> getAll(Pageable pageable);
    Institution getDescriptionInstitutionById(Integer id) throws InstitutionNotFoundException;
    Institution addInstitution(InstitutionInDto institutionInDto/*String name, String address, String description,LocalDate foundationDate*/) throws FoundationDateIsExpiredException, NumberParseException;
    LocalDate getFoundationDate(Integer id) throws InstitutionNotFoundException;
    void refactorInstitutionById(Integer id, String description) throws InstitutionNotFoundException;


}
