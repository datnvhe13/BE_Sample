package com.vti.testing.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentNameValidator.class)
public @interface DepartmentNameNotExists {
    String message() default "{Department.createDepartment.form.name.NotExists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
