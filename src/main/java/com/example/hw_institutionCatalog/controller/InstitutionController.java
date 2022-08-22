package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.mapper.InstitutionMapper;
import com.example.hw_institutionCatalog.service.InstitutionService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class InstitutionController {
    private final InstitutionService institutionService;
    private final InstitutionMapper institutionMapper;

    @Autowired
    private InstitutionMapper mapper;

    public InstitutionController(InstitutionService service, InstitutionMapper institutionMapper, InstitutionMapper mapper) {
        this.institutionService = service;
        this.institutionMapper = institutionMapper;
        this.mapper = mapper;
    }

    @GetMapping("getall")
    public Page<InstitutionOutDto> getAllInstitutions(@PageableDefault(sort = "name") Pageable pageable) {
        Page<Institution> institutions = institutionService.getAll(pageable);
        return institutions.map(institutionMapper::mapInstitutionToInstitutionOutDto);
    }

    @GetMapping("desk/{id}")
    public InstitutionOutDto getDescriptionInstitutionById(@PathVariable("id") Integer id)
            throws InstitutionNotFoundException {
        Institution institutionDesk = institutionService.getDescriptionInstitutionById(id);
        return mapper.mapInstitutionToInstitutionOutDto(institutionDesk);
    }

    @PostMapping("add/inst")
//    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionOutDto addInstitution(@RequestParam(value = "name") String name,
                               @RequestParam(value = "address") String address,
                               @RequestParam(value = "description") String description,
                               @RequestParam(value = "foundation_date")
                               @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate foundationDate,
                               @RequestParam(value = "telephone_number") String telephoneNumber)
            throws FoundationDateIsExpiredException, NumberParseException {
        InstitutionInDto institutionInDto = InstitutionInDto.builder()
                .name(name)
                .address(address)
                .description(description)
                .foundationDate(foundationDate)
                .telephoneNumber(telephoneNumber)
                .build();
        Institution institution = institutionService.addInstitution(institutionInDto);
        return mapper.mapInstitutionToInstitutionOutDto(institution);
    }

    @PostMapping("institution")
    public InstitutionOutDto createInstitution(@Valid @RequestBody InstitutionInDto institutionInDto) throws NumberParseException, FoundationDateIsExpiredException {
        Institution institution = institutionService.addInstitution(institutionInDto);
        return mapper.mapInstitutionToInstitutionOutDto(institution);
    }

    @PostMapping("ref/inst/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void refactorInstitutionById(@RequestParam (value = "description") String description,
                                        @PathVariable Integer id) throws InstitutionNotFoundException {
        institutionService.refactorInstitutionById(id, description);

    }
}
