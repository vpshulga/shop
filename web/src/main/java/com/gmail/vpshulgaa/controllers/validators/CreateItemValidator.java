package com.gmail.vpshulgaa.controllers.validators;

import com.gmail.vpshulgaa.service.dto.ItemDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("createItemValidator")
public class CreateItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "item.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "item.price.empty");

        ItemDto item = (ItemDto) o;

        if (item.getPrice() != null && item.getPrice().signum() != 1) {
            errors.rejectValue("price", "not.positive.price");
        }
    }
}
