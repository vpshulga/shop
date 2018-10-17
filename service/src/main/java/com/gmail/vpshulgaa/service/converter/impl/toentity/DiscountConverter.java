package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import org.springframework.stereotype.Component;

@Component("discountConverter")
public class DiscountConverter implements Converter<DiscountDto, Discount> {

    @Override
    public Discount toEntity(DiscountDto dto) {
        if (dto == null) {
            return null;
        }
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setName(dto.getName());
        discount.setPercent(dto.getPercent());
        discount.setExpireDate(dto.getExpireDate());
        return discount;
    }
}
