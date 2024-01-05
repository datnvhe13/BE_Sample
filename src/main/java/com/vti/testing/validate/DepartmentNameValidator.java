package com.vti.testing.validate;

import com.vti.testing.repository.IDepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentNameValidator implements ConstraintValidator<DepartmentNameNotExists, String> {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public void initialize(DepartmentNameNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !departmentRepository.existsByName(name);
    }
}
