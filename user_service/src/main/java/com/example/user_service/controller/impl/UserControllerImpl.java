package com.example.user_service.controller.impl;

import com.example.user_service.controller.UserController;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    @Override
    public UserOutDto createUser(UserInDto userInDto) {
        return null;
    }

    @Override
    public UserOutDto updateUser(UserInDto userInDto, Long id) {
        return null;
    }

    @Override
    public Long deleteUser(Long id) {
        return null;
    }

    @Override
    public UserOutDto getUser(Long id) {
        return null;
    }
}
