package com.example.user_service.dto.in;

import lombok.Data;

@Data
public class ChangeOwnerInDto {
    private Integer oldOwnerId;
    private Integer newOwnerId;
}
