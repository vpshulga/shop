package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("createUserValidator")
public class CreateUserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public CreateUserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");

        UserProfileDto user = (UserProfileDto) o;

        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(user.getEmail()).matches())) {
            errors.rejectValue("email", "user.email.invalid");
        }

        if (userService.isExistsEmail(user.getEmail())) {
            errors.rejectValue("email", "user.email.exists");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "diff.password");
        }
    }
}
