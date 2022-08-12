package com.example.hw_institutionCatalog.service.impl;

import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.mapper.InstitutionMapper;
import com.example.hw_institutionCatalog.repository.InstitutionRepository;
import com.example.hw_institutionCatalog.service.InstitutionService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, InstitutionMapper institutionMapper) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
    }


    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution getDescriptionInstitutionById(Integer id) throws InstitutionNotFoundException {
        Optional<Institution> byId = institutionRepository.findById(id);
        if (byId.isEmpty()){
            throw new InstitutionNotFoundException(id);
        }
        return byId.get();
    }


    @Override
    public Institution addInstitution(InstitutionInDto institutionInDto/*String name, String address, String description, LocalDate foundationDate*/) throws FoundationDateIsExpiredException, NumberParseException {
        if (institutionInDto.getFoundationDate() == null|| LocalDate.now().isBefore(institutionInDto.getFoundationDate())){
            throw new FoundationDateIsExpiredException(institutionInDto.getName(), institutionInDto.getFoundationDate());
        }
        Institution institution = institutionMapper.mapInstitutionInDtoToInstitution(institutionInDto);
        return institutionRepository.save(institution);
    }



    @Override
    public LocalDate getFoundationDate(Integer id) throws InstitutionNotFoundException {
        Institution institutionById = getDescriptionInstitutionById(id);
        return institutionById.getFoundationDate();
    }

    @Override
    public void refactorInstitutionById(Integer id, String description) throws InstitutionNotFoundException {
        Optional<Institution> byId = institutionRepository.findById(id);
        if (byId.isPresent()){
            Institution institution = byId.get();
            institution.setDescription(description);
            institutionRepository.save(institution);
        } else {
            throw new InstitutionNotFoundException(id);
        }
    }
}
