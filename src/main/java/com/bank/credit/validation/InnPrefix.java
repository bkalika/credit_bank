package com.bank.credit.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InnConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InnPrefix {
    String message() default "Inn must not be started from 0 and contains 10 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
