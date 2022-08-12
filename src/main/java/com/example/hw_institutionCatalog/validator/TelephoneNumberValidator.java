package com.example.hw_institutionCatalog.validator;

import com.example.hw_institutionCatalog.util.Util;
import com.example.hw_institutionCatalog.validator.annotation.TelephoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneNumberValidator implements
        ConstraintValidator<TelephoneNumberConstraint, String> {

    @Override
    public void initialize(TelephoneNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String telephone,
                           ConstraintValidatorContext cxt) {
        if(telephone != null) {
            return Util.checkRuTelephone(telephone);
        }
        return false;
    }

}