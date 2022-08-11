package com.example.hw_institutionCatalog.service;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

public class InstitutionServiceTest extends AppContextTest {
    @Autowired
    private InstitutionService institutionService;

    @Test
    void getAll() {
        List<Institution> all = institutionService.getAll();
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    void addInstitution() throws FoundationDateIsExpiredException, InstitutionNotFoundException, NumberParseException {
        MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
        LocalDate defaultNow = LocalDate.of(2022, 6, 7);
        localDateMockedStatic.when(LocalDate::now).thenReturn(defaultNow);

        InstitutionInDto institutionInDtoAfter = InstitutionInDto.builder()
                .name("test")
                .address( "address_test")
                .description("description_test")
                .telephoneNumber("8 958 526 33 63")
                .foundationDate(LocalDate.of(2022, 8, 26))
                .build();
        Assertions.assertThrowsExactly(FoundationDateIsExpiredException.class,
                () -> institutionService.addInstitution(institutionInDtoAfter),
                "Restaurant with name \"" + "test" + "\"" +
                        "has foundation date " + LocalDate.now());
        InstitutionInDto institutionInDtoPrevious = InstitutionInDto.builder()
                .name("test")
                .address( "address_test")
                .description("description_test")
                .telephoneNumber("+79585263363")
                .foundationDate(LocalDate.of(2012, 12, 12))
                .build();

        Institution institution = institutionService.addInstitution(institutionInDtoPrevious);
        LocalDate foundationDate = institutionService.getFoundationDate(institution.getId());
        assertEquals(LocalDate.of(2012, 12, 12), foundationDate);
    }

    @Test
    void getDescriptionInstitutionById() {
        int id = 1;
        Institution institution = institutionService.getDescriptionInstitutionById(id);
        assertEquals(institution.getId(), id);
        System.out.println(institution.getDescription());
    }

    @Test
    void refactorInstitutionById() throws InstitutionNotFoundException {
        int id = 5;
        String description = "description_test";
        String oldDescription = institutionService.getDescriptionInstitutionById(id).getDescription();
        institutionService.refactorInstitutionById(id, description);
        String newDescription = institutionService.getDescriptionInstitutionById(id).getDescription();
        assertNotEquals(oldDescription, newDescription);
    }
}
