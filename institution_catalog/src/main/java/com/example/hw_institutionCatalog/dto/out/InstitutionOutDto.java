package com.example.hw_institutionCatalog.dto.out;

import com.example.hw_institutionCatalog.entity.CuisineType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "dto for display institution")
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InstitutionOutDto{
    private final Integer id;
    private final String name;
    private final String address;
    private final String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private final LocalDate foundationDate;
    private final String telephoneNumber;
    private final List<ReviewOutDto> reviewList;
    private final Integer ownerId;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssX")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private final LocalDateTime updateDateTime;
    private final CuisineType cuisineType;
}
