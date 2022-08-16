package com.example.hw_institutionCatalog.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @param foundationDate @JsonSerialize(using = LocalDateSerializer.class)    @DateTimeFormat(pattern = "YYYY-MM-DD")
 */
//@Getter
//@RequiredArgsConstructor
@Builder
//@EqualsAndHashCode
public record InstitutionOutDto(Integer id, String name, String address, String description, String telephoneNumber,
                                String email, List<ReviewOutDto> reviewList,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate foundationDate) {
}
