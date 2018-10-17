package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.NewsDto;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("newsValidator")
public class NewsValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(NewsValidator.class);

    private final MessageSource messageSource;

    @Autowired
    public NewsValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewsDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        NewsDto news = (NewsDto) o;

        if (news.getTitle().isEmpty()) {
            errors.rejectValue("title", "news.title.empty");
            logger.info(messageSource.getMessage("news.title.empty", null, Locale.ENGLISH));
        }

        if (news.getTitle().length() > 200) {
            errors.rejectValue("title", "news.title.length.long");
            logger.info(messageSource.getMessage("news.title.length.long", null, Locale.ENGLISH));
        }

        if (news.getContent().isEmpty()) {
            errors.rejectValue("content", "news.content.empty");
            logger.info(messageSource.getMessage("news.content.empty", null, Locale.ENGLISH));
        }

        if (news.getContent().length() > 3000) {
            errors.rejectValue("content", "news.content.length.long");
            logger.info(messageSource.getMessage("news.content.length.long", null, Locale.ENGLISH));
        }
    }
}
