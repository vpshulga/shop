package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("createUserValidator")
public class CreateUserValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(CreateUserValidator.class);

    private final UserService userService;

    private final MessageSource messageSource;

    @Autowired
    public CreateUserValidator(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {


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


        if (user.getEmail().isEmpty()) {
            errors.rejectValue("email", "user.email.empty");
            logger.info(messageSource.getMessage("user.email.empty", null, Locale.ENGLISH));
        }

        if (user.getEmail().length() > 150) {
            errors.rejectValue("email", "user.email.length.long");
            logger.info(messageSource.getMessage("user.email.length.long", null, Locale.ENGLISH));
        }

        if (user.getAddress().length() > 200) {
            errors.rejectValue("address", "user.address.length.long");
            logger.info(messageSource.getMessage("user.address.length.long", null, Locale.ENGLISH));
        }

        if (user.getTelephone().length() > 20) {
            errors.rejectValue("telephone", "user.telephone.length.long");
            logger.info(messageSource.getMessage("user.telephone.length.long", null, Locale.ENGLISH));
        }

        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(user.getEmail()).matches())) {
            errors.rejectValue("email", "user.email.invalid");
            logger.info(messageSource.getMessage("user.email.invalid", null, Locale.ENGLISH));
        }

        if (userService.isExistsEmail(user.getEmail())) {
            errors.rejectValue("email", "user.email.exists");
            logger.info(messageSource.getMessage("user.email.exists", null, Locale.ENGLISH));
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "user.password.not.confirm");
            logger.info(messageSource.getMessage("user.password.not.confirm", null, Locale.ENGLISH));
        }
    }
}
