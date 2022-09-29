package com.example.user_service.service;

import com.example.user_service.dto.in.ChangePasswordInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.EmailException;
import com.example.user_service.exception.UserNotFoundException;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UserService {

    UserOutDto createUser(UserInDto userInDto) throws EmailException;
    UserOutDto updateUser(UserInDto userInDto, Long id)throws UserNotFoundException;
    Long deleteUser(Long id)throws UserNotFoundException;
    UserOutDto getUser(Long id) throws UserNotFoundException;

    @Transactional
    void changePassword(ChangePasswordInDto changePasswordInDto) throws UserNotFoundException;
}
