package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("updateUserValidator")
public class UpdateUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfileDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
    }
}
