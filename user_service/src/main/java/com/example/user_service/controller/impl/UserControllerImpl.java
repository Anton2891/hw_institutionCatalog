package com.example.user_service.controller.impl;

import com.example.user_service.controller.UserController;
import com.example.user_service.dto.in.ChangeOwnerInDto;
import com.example.user_service.dto.in.ChangePasswordInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.ChangeOwnerOutDto;
import com.example.user_service.dto.out.DeletedOwnerOutDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.service.impl.UserServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;
    private final RabbitMessageOperations rabbitTemplate;

    public UserControllerImpl(UserServiceImpl userService, RabbitMessageOperations rabbitTemplate) {
        this.userService = userService;
        this.rabbitTemplate = rabbitTemplate;
    }

//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

//    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String user() {
        return "user";
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
        Long idDell = userService.deleteUser(id);
//        rabbitTemplate.convertAndSend("myQueue", new DeletedOwnerOutDto(id));
        rabbitTemplate.convertSendAndReceive("myQueue", new DeletedOwnerOutDto(id), DeletedOwnerOutDto.class);
        return idDell;
    }

    @Override
    public void changeOwner(ChangeOwnerInDto changeOwnerInDto){
        rabbitTemplate.convertSendAndReceive("myQueue",
                new ChangeOwnerOutDto(changeOwnerInDto.getOldOwnerId(),
                        changeOwnerInDto.getNewOwnerId()), ChangeOwnerOutDto.class);
    }

    @Override
    public UserOutDto getUser(Long id) throws UserNotFoundException {
//        rabbitTemplate.convertAndSend("myQueue", "Hello, world!");
        rabbitTemplate.convertSendAndReceive("myQueue", "Hello, world!", String.class);
        return userService.getUser(id);
    }

    @Override
    public void changePassword(ChangePasswordInDto changePasswordInDto) throws UserNotFoundException {
        userService.changePassword(changePasswordInDto);
    }
}
