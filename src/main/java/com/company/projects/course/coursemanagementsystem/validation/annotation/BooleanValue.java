package com.company.projects.course.coursemanagementsystem.validation.annotation;

import com.company.projects.course.coursemanagementsystem.validation.validator.BooleanValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BooleanValueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BooleanValue {
    String message() default "This value is not a boolean";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payloads() default {};
}
