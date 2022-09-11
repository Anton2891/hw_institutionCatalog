package com.example.hw_institutionCatalog.dto.in;

import lombok.Data;

@Data
public class ChangeOwnerInDto {
    private Integer oldOwnerId;
    private Integer newOwnerId;
}
