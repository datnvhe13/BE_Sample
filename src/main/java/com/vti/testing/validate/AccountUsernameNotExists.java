package com.vti.testing.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountUsernameNotExistsValidator.class)
public @interface AccountUsernameNotExists {
    String message() default "Account username exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
