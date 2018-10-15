package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.CommentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("commentValidator")
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CommentDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "content", "comment.content.empty");
    }
}
