package com.easyLife.taskManagement.taskManagement.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {IbanImpl.class})
public @interface IbanValidation {
    String message() default "isbn Code is not in valid Pattern.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
