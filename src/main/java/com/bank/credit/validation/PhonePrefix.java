package com.bank.credit.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhonePrefix {
    String message() default "Phone must be started from 0 and contains 8 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
