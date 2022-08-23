package com.example.hw_institutionCatalog.dto.in;

import com.example.hw_institutionCatalog.entity.Review;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class InstitutionInDto {

    @NotBlank(message = "пустое имя")
    private final String name;
    private final String address;
    private final String description;

    @NotBlank(message = "пустой телефонный номер")
    private final String telephoneNumber;
    private final String email;

    @Past(message = "будущее")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate foundationDate;
    private final List<Review> reviewList;
}
