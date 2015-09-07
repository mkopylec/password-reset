package com.github.mkopylec.passwordreset.api.validation;

import com.github.mkopylec.passwordreset.api.dto.ResetMethod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL;
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE;

class AllowedResetMethodValidator implements ConstraintValidator<AllowedResetMethod, ResetMethod> {

    @Override
    public void initialize(AllowedResetMethod constraint) {
    }

    @Override
    public boolean isValid(ResetMethod value, ConstraintValidatorContext context) {
        return value == FULL || value == SIMPLE;
    }
}
