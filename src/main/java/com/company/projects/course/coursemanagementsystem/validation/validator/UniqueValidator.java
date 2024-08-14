package com.company.projects.course.coursemanagementsystem.validation.validator;

import com.company.projects.course.coursemanagementsystem.validation.annotation.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final ApplicationContext applicationContext;

    @Autowired
    public UniqueValidator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    private boolean caseSensitive;
    private String fieldName;
    private Class<?> repositoryClass;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.caseSensitive = constraintAnnotation.caseSensitive();
        this.fieldName = constraintAnnotation.fieldName();
        this.repositoryClass = constraintAnnotation.repository();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        Object repository = applicationContext.getBean(repositoryClass);

        try {
            Method method = caseSensitive
                    ? repositoryClass.getMethod("existsBy" + capitalize(fieldName), String.class)
                    : repositoryClass.getMethod("existsBy" + capitalize(fieldName) + "IgnoreCase", String.class);
            Boolean exists = (Boolean) method.invoke(repository, value);
            return !exists;
        } catch (Exception e) {
            throw new RuntimeException("Error accessing repository method", e);
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
