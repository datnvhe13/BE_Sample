package com.vti.testing.validate;

import com.vti.testing.repository.IDepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentIdExistsValidator
            implements ConstraintValidator<DepartmentIdExists, Integer> {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public void initialize(DepartmentIdExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return departmentRepository.existsById(id);
    }
}
