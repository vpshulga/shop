package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.CommentDto;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("commentValidator")
public class CommentValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(CommentValidator.class);

    private final MessageSource messageSource;

    @Autowired
    public CommentValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CommentDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CommentDto comment = (CommentDto) o;

        if (comment.getContent().isEmpty()) {
            errors.rejectValue("content", "comment.content.empty");
            logger.info(messageSource.getMessage("comment.content.empty", null, Locale.ENGLISH));
        }

        if (comment.getContent().length() > 1000) {
            errors.rejectValue("content", "comment.content.length.long");
            logger.info(messageSource.getMessage("comment.content.length.long", null, Locale.ENGLISH));
        }


    }
}
