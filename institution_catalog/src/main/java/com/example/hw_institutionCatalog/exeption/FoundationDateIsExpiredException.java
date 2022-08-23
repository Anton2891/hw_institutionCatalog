package com.example.hw_institutionCatalog.exeption;

import java.time.LocalDate;

public class FoundationDateIsExpiredException extends Exception{
    private final String institutionName;
    private final LocalDate foundationDate;


    public FoundationDateIsExpiredException(String institutionName, LocalDate foundationDate) {
        this.institutionName = institutionName;
        this.foundationDate = foundationDate;
    }

    @Override
    public String toString() {
        return "institution with name \"" + institutionName + "\"" +
                "has foundation date " + foundationDate;
    }
}
