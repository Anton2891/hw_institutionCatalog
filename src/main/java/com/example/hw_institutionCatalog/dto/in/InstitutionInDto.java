package com.example.hw_institutionCatalog.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class InstitutionInDto {
    private final Integer id;

    @NotBlank(message = "пустое имя")
    private final String name;
    private final String address;
    private final String description;

    @NotBlank(message = "пустой телефонный номер")
    private final String telephoneNumber;
    private final String email;

    @Past(message = "будущее")
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private final LocalDate foundationDate;
}
