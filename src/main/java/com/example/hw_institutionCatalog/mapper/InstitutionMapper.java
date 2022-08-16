package com.example.hw_institutionCatalog.mapper;

import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.entity.Review;
import com.google.i18n.phonenumbers.NumberParseException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class InstitutionMapper {

    @Autowired
    private ReviewMapper reviewMapper;

    public abstract InstitutionOutDto mapInstitutionToInstitutionOutDto(Institution institution);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telephoneNumber",
            expression = "java(com.example.hw_institutionCatalog.util.Util.reformatRuTelephone(institutionInDto.getTelephoneNumber()))"
    )

    public abstract Institution mapInstitutionInDtoToInstitution(InstitutionInDto institutionInDto) throws NumberParseException;

    ReviewOutDto reviewToReviewOutDto(Review review){
        return reviewMapper.mapReviewToReviewOutDto(review);
    }
}
