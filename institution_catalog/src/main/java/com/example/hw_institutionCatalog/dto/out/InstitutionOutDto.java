package com.example.hw_institutionCatalog.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private final LocalDateTime updateDateTime;
}
