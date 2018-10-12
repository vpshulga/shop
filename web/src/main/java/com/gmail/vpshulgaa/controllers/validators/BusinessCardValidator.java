package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("businessCardValidator")
public class BusinessCardValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return BusinessCardDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "card.title.empty");

        BusinessCardDto businessCard = (BusinessCardDto) o;

        if (businessCard.getWorkingTelephone().length() > 20) {
            errors.rejectValue("workingTelephone", "too.long.telephone");
        }
    }
}
