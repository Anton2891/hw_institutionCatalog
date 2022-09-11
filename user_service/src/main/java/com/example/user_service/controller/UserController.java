package com.example.user_service.controller;

import com.example.user_service.dto.in.ChangeOwnerInDto;
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

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is create"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User is not create"
            )
    })
    @PostMapping
    UserOutDto createUser(@Valid @RequestBody UserInDto userInDto);

    @Operation(summary = "Update user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is update"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User is not update"
            )
    })
    @PutMapping("/{id}")
    UserOutDto updateUser(@RequestBody UserInDto userInDto, @PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is delete"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @DeleteMapping("/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Change owner to InstitutionDB")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PutMapping("owner")
    void changeOwner(@RequestBody ChangeOwnerInDto changeOwnerInDto);

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @GetMapping("/{id}")
    UserOutDto getUser(@PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Change password by user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "password is update"),
            @ApiResponse(
                    responseCode = "404",
                    description = "password is not valid"
            )
    })
    @PutMapping("/{email}/password")
    void changePassword(@PathVariable String email,@Valid @RequestBody ChangePasswordInDto changePasswordInDto)
            throws UserNotFoundException;
}
