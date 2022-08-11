package com.example.hw_institutionCatalog.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class InstitutionOutDto {
    private final Integer id;
    private final String name;
    private final String address;
    private final String description;
    private final String telephoneNumber;
    private final String email;
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private final LocalDate foundationDate;
}
