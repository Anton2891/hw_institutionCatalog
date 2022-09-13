package com.example.hw_institutionCatalog.dto.out;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InstitutionSmallOutDto {
    private Integer id;
    private String name;
    private BigDecimal average;
}
