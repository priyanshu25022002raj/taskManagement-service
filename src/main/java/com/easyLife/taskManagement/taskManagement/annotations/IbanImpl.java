package com.easyLife.taskManagement.taskManagement.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IbanImpl implements ConstraintValidator<IbanValidation,String> {
    private static final String ISBN_PATTERN = "^isbn_\\d{5}$";
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(ISBN_PATTERN);
    }
}
