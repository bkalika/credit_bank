package com.bank.credit.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PhoneConstraintValidator implements ConstraintValidator<PhonePrefix, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("0");
    }
}
