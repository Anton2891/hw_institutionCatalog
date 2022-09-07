package com.example.user_service.controller;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RequestMapping("/role")
public interface RoleController {

    @Operation(summary = "Create role")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "role is create"),
            @ApiResponse(
                    responseCode = "404",
                    description = "role is not create"
            )
    })
    @PostMapping
    RoleOutDto createRole(@RequestBody RoleInDto roleInDto) throws RoleNotFoundException;

    @Operation(summary = "Delete role by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "role is delete"),
            @ApiResponse(
                    responseCode = "404",
                    description = "role is not delete"
            )
    })
    @DeleteMapping( "/{id}")
    Long deleteRole (@PathVariable Long id) throws RoleNotFoundException;

//    @PostMapping("/add/user/{user_id}")
//    String addRole(@PathVariable Long id);
}
