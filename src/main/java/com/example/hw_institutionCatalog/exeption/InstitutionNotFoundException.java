package com.example.hw_institutionCatalog.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstitutionNotFoundException extends Exception{
    private final Integer institutionId;

    public InstitutionNotFoundException(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getInstitutionId(){
        return institutionId;
    }
}
