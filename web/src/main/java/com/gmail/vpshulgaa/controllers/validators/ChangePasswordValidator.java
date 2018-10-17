package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("changePasswordValidator")
public class ChangePasswordValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(ChangePasswordValidator.class);

    private final MessageSource messageSource;

    @Autowired
    public ChangePasswordValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePasswordDto.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "change.password.new.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "confirm.password.empty");

        ChangePasswordDto changePasswordDto = (ChangePasswordDto) o;

        if (changePasswordDto.getNewPassword().isEmpty()) {
            errors.rejectValue("newPassword", "change.password.new.password.empty");
            logger.info(messageSource.getMessage("change.password.new.password.empty", null, Locale.ENGLISH));
        }

        if (changePasswordDto.getConfirmPassword().isEmpty()) {
            errors.rejectValue("confirmPassword", "change.password.confirm.password.empty");
            logger.info(messageSource.getMessage("change.password.confirm.password.empty", null, Locale.ENGLISH));
        }

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "change.password.password.not.confirm");
            logger.info(messageSource.getMessage("change.password.password.not.confirm", null, Locale.ENGLISH));
        }
    }
}
