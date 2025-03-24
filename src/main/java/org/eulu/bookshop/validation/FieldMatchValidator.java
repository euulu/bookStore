package org.eulu.bookshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.eulu.bookshop.annotation.FieldMatch;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, String> {
    private String passwordField;
    private String repeatPasswordField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.passwordField = constraintAnnotation.password();
        this.repeatPasswordField = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String passwordValue = (String) new BeanWrapperImpl(value).getPropertyValue(passwordField);
        String repeatPasswordValue = (String) new BeanWrapperImpl(value)
                .getPropertyValue(repeatPasswordField);

        return passwordValue != null && passwordValue.equals(repeatPasswordValue);
    }
}
