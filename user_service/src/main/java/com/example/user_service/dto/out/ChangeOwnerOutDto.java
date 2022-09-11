package com.example.user_service.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeOwnerOutDto {
    private Integer oldOwnerId;
    private Integer newOwnerId;
}
