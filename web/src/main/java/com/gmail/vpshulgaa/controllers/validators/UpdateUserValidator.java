package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("updateUserValidator")
public class UpdateUserValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(UpdateUserValidator.class);

    private final MessageSource messageSource;

    @Autowired
    public UpdateUserValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfileDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserProfileDto user = (UserProfileDto) o;

        if (user.getName().isEmpty()) {
            errors.rejectValue("name", "user.name.empty");
            logger.info(messageSource.getMessage("user.name.empty", null, Locale.ENGLISH));
        }

        if (user.getName().length() > 50) {
            errors.rejectValue("name", "user.name.length.long");
            logger.info(messageSource.getMessage("user.name.length.long", null, Locale.ENGLISH));
        }

        if (user.getSurname().length() > 50) {
            errors.rejectValue("surname", "user.surname.length.long");
            logger.info(messageSource.getMessage("user.surname.length.long", null, Locale.ENGLISH));
        }

        if (user.getAddress().length() > 200) {
            errors.rejectValue("address", "user.address.length.long");
            logger.info(messageSource.getMessage("user.address.length.long", null, Locale.ENGLISH));
        }

        if (user.getTelephone().length() > 20) {
            errors.rejectValue("telephone", "user.telephone.length.long");
            logger.info(messageSource.getMessage("user.telephone.length.long", null, Locale.ENGLISH));
        }
    }
}
