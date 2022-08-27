package com.example.user_service.controller.impl;

import com.example.user_service.controller.UserController;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;

    public UserControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserOutDto createUser(UserInDto userInDto) {

        return userService.createUser(userInDto);
    }

    @Override
    public UserOutDto updateUser(UserInDto userInDto, Long id) throws UserNotFoundException {
        return userService.updateUser(userInDto, id);
    }

    @Override
    public Long deleteUser(Long id) throws UserNotFoundException {
        return userService.deleteUser(id);
    }

    @Override
    public UserOutDto getUser(Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }
}
