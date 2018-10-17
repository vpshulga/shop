package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.ItemDto;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("createItemValidator")
public class CreateItemValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(CreateItemValidator.class);

    private final MessageSource messageSource;

    @Autowired
    public CreateItemValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ItemDto item = (ItemDto) o;

        if (item.getName().isEmpty()) {
            errors.rejectValue("name", "item.name.empty");
            logger.info(messageSource.getMessage("item.name.empty", null, Locale.ENGLISH));
        }

        if (item.getName().length() > 200) {
            errors.rejectValue("name", "item.name.length.long");
            logger.info(messageSource.getMessage("item.name.length.long", null, Locale.ENGLISH));
        }

        if (item.getDescription().length() > 1500) {
            errors.rejectValue("description", "item.description.length.long");
            logger.info(messageSource.getMessage("item.description.length.long", null, Locale.ENGLISH));
        }

        if (item.getPrice() == null) {
            errors.rejectValue("price", "item.price.empty");
            logger.info(messageSource.getMessage("item.price.empty", null, Locale.ENGLISH));
        }

        if (item.getPrice() != null && item.getPrice().signum() != 1) {
            errors.rejectValue("price", "item.price.not.positive");
            logger.info(messageSource.getMessage("item.price.not.positive", null, Locale.ENGLISH));
        }
    }
}
