package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import org.springframework.stereotype.Component;

@Component("discountDtoConverter")
public class DiscountDtoConverter implements DtoConverter<DiscountDto, Discount> {

    @Override
    public DiscountDto toDto(Discount entity) {
        if (entity == null) {
            return null;
        }
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(entity.getId());
        discountDto.setName(entity.getName());
        discountDto.setPercent(entity.getPercent());
        discountDto.setExpireDate(entity.getExpireDate());
        return discountDto;
    }
}
