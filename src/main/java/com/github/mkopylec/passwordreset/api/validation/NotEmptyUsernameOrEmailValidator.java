package com.github.mkopylec.passwordreset.api.validation;

import com.github.mkopylec.passwordreset.api.dto.UserData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

class NotEmptyUsernameOrEmailValidator implements ConstraintValidator<NotEmptyUsernameOrEmail, UserData> {

    @Override
    public void initialize(NotEmptyUsernameOrEmail constraint) {
    }

    @Override
    public boolean isValid(UserData value, ConstraintValidatorContext context) {
        return isNotBlank(value.getUsername()) || isNotBlank(value.getEmail());
    }
}
