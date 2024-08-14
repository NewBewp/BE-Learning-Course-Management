package com.company.projects.course.coursemanagementsystem.validation.validator;

import com.company.projects.course.coursemanagementsystem.validation.annotation.BooleanValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BooleanValueValidator implements ConstraintValidator<BooleanValue, Boolean> {
    @Override
    public void initialize(BooleanValue booleanValue) {}

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && (value.equals(Boolean.TRUE) || value.equals(Boolean.FALSE));
    }
}
