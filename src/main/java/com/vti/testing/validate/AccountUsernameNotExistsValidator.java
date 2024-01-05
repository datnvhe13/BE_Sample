package com.vti.testing.validate;

import com.vti.testing.repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountUsernameNotExistsValidator
        implements ConstraintValidator<AccountUsernameNotExists, String> {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public void initialize(AccountUsernameNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !accountRepository.existsByUsername(username);
    }
}
