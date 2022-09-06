package com.example.user_service.controller;

import com.example.user_service.dto.in.ChangePasswordInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
public interface UserController {

    @PostMapping
    UserOutDto createUser(@Valid @RequestBody UserInDto userInDto);

    @PutMapping("/{id}")
    UserOutDto updateUser(@RequestBody UserInDto userInDto, @PathVariable Long id) throws UserNotFoundException;


    @DeleteMapping("/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users")
    })


            @Operation(summary = "Gets all users")
    @GetMapping("/{id}")
    UserOutDto getUser(@PathVariable Long id) throws UserNotFoundException;

//    @PutMapping("/password")
//    void changePassword(@Valid @PathVariable String email @RequestBody ChangePasswordInDto changePasswordInDto) throws UserNotFoundException;

    @PutMapping("/{email}/password")
    void changePassword(@PathVariable String email,@Valid @RequestBody ChangePasswordInDto changePasswordInDto) throws UserNotFoundException;

}
