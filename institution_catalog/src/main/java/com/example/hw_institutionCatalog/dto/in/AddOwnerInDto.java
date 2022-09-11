package com.example.hw_institutionCatalog.dto.in;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class AddOwnerInDto {
    private Integer ownerId;
    private Integer institutionId;
}
