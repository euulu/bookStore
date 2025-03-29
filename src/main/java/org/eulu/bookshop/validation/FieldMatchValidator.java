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
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        String passwordValue = (String) beanWrapper.getPropertyValue(passwordField);
        String repeatPasswordValue = (String) beanWrapper.getPropertyValue(repeatPasswordField);
        if (passwordValue == null || repeatPasswordValue == null) {
            return false;
        }
        return passwordValue.equals(repeatPasswordValue);
    }
}
