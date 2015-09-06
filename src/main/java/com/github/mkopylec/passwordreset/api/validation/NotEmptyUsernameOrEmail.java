package com.github.mkopylec.passwordreset.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NotEmptyUsernameOrEmailValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface NotEmptyUsernameOrEmail {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
