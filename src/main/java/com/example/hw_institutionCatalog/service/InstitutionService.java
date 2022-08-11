package com.example.hw_institutionCatalog.service;


import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.google.i18n.phonenumbers.NumberParseException;

import java.time.LocalDate;
import java.util.List;

public interface InstitutionService {
    List<Institution> getAll();
    Institution getDescriptionInstitutionById(Integer id);
    Institution addInstitution(InstitutionInDto institutionInDto/*String name, String address, String description,LocalDate foundationDate*/) throws FoundationDateIsExpiredException, NumberParseException;
    LocalDate getFoundationDate(Integer id) throws InstitutionNotFoundException;
    void refactorInstitutionById(Integer id, String description) throws InstitutionNotFoundException;

}
