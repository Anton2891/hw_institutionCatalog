package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.dto.in.AddOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.DeleteOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionSmallOutDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.exeption.FoundationDateIsExpiredException;
import com.example.hw_institutionCatalog.exeption.InstitutionNotFoundException;
import com.example.hw_institutionCatalog.repository.data.InstitutionSmall;
import com.google.i18n.phonenumbers.NumberParseException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/inst")
public interface InstitutionController {
    @Operation(summary = "Gets all institutions")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @GetMapping
    Page<InstitutionOutDto> getAllInstitutions(@PageableDefault(sort = "name") Pageable pageable);

    @Operation(summary = "Get description institution by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @GetMapping("/{id}")
    InstitutionOutDto getDescriptionInstitutionById(@PathVariable("id") Integer id)
            throws InstitutionNotFoundException;

    @Operation(summary = "Create institution")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @PostMapping
    InstitutionOutDto createInstitution(@Valid @RequestBody InstitutionInDto institutionInDto)
            throws NumberParseException, FoundationDateIsExpiredException;

    @Operation(summary = "Refactor description for institution by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @PutMapping("/{id}/description")
    void refactorInstitutionById(@RequestParam (value = "description") String description,
                                 @PathVariable Integer id) throws InstitutionNotFoundException;

    @Operation(summary = "Get review for institution by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @GetMapping("/{id}/reviews")
    Page<ReviewOutDto> getReviewInstitutionById(@PageableDefault(sort = "name")
                                                @PathVariable("id") Integer id, Pageable pageable)
            throws InstitutionNotFoundException;

    @Operation(summary = "Get rating for institution by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @GetMapping("/{id}/rating")
    Double getRatingInstitutionById(@PathVariable("id") Integer id,
                                    @PageableDefault(sort = "name") Pageable pageable)
            throws InstitutionNotFoundException;

    @Operation(summary = "Add review for institution by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok"),
            @ApiResponse(
                    responseCode = "404",
                    description = "false"
            )
    })
    @PostMapping("/{id}/review")
    void addReview(@PathVariable Integer id,
                   @RequestParam(value = "rating") Integer rating,
                   @RequestParam(value = "review") String review) throws InstitutionNotFoundException;

    @Operation(summary = "delete owner")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @DeleteMapping("/owner")
    void deleteOwner(@RequestBody DeleteOwnerInDto deleteOwnerInDto) throws InstitutionNotFoundException;

    @Operation(summary = "add owner")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PostMapping("/owner")
    void addOwner(@RequestBody AddOwnerInDto addOwnerInDto) throws InstitutionNotFoundException;

    @Operation(summary = "change owner")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PostMapping("/owner/change")
    void changeOwner(@RequestBody ChangeOwnerInDto changeOwnerInDto) throws InstitutionNotFoundException;

    @GetMapping("/smallList")
    List<InstitutionSmallOutDto> getSmallList();

    @DeleteMapping("/{id}")
    void deleteInstitutionById(@PathVariable Integer id) throws InstitutionNotFoundException;

}
