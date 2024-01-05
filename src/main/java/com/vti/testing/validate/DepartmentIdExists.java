package com.vti.testing.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentIdExistsValidator.class)
public @interface DepartmentIdExists {
    String message() default "Department id not exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
