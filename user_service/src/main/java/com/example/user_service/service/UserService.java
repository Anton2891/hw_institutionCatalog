package com.example.user_service.service;

import com.example.user_service.dto.in.ChangePasswordInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.UserNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UserService {

    UserOutDto createUser(UserInDto userInDto);
    UserOutDto updateUser(UserInDto userInDto, Long id)throws UserNotFoundException;
    Long deleteUser(Long id)throws UserNotFoundException;
    UserOutDto getUser(Long id) throws UserNotFoundException;
    void changePassword(ChangePasswordInDto changePasswordInDto, String email) throws UserNotFoundException;
}
