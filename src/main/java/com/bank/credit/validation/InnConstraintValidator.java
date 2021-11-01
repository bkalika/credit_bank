package com.bank.credit.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class InnConstraintValidator implements ConstraintValidator<InnPrefix, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return !value.toString().startsWith("0");
    }
}
