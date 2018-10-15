package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.NewsDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("newsValidator")
public class NewsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewsDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "news.title.empty");
        ValidationUtils.rejectIfEmpty(errors, "content", "news.content.empty");
    }
}
