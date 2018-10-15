package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("changePasswordValidator")
public class ChangePasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePasswordDto.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "new.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "confirm.password.empty");

        ChangePasswordDto changePasswordDto = (ChangePasswordDto) o;

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            errors.rejectValue("newPassword", "diff.password");
        }
    }
}
