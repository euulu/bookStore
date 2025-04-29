package org.eulu.bookshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.eulu.bookshop.annotation.FieldMatch;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String passwordField;
    private String repeatPasswordField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.passwordField = constraintAnnotation.password();
        this.repeatPasswordField = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(passwordField);
        Object repeatPasswordValue = new BeanWrapperImpl(value)
                .getPropertyValue(repeatPasswordField);
        if (passwordValue == null) {
            return repeatPasswordValue == null;
        }
        return passwordValue.equals(repeatPasswordValue);
    }
}
