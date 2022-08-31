package com.example.user_service.mapper;

import com.example.user_service.dto.in.UserRoleInDto;
import com.example.user_service.dto.out.UserRoleOutDto;
import com.example.user_service.entity.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleOutDto userRoleToUserRoleOutDto(UserRole userRole);
    UserRole userRoleInDtoToUserRole(UserRoleInDto userRoleInDto);
}