package com.example.user_service.mapper;

import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutDto userToUserOutDto(User user);
    User userInDtoToUser(UserInDto userInDto);
}
