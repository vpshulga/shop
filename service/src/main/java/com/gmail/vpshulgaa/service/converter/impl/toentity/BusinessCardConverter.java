package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.BusinessCard;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("businessCardConverter")
public class BusinessCardConverter implements Converter<BusinessCardDto, BusinessCard> {
    @Override
    public BusinessCard toEntity(BusinessCardDto dto) {
        if (dto == null) {
            return null;
        }
        BusinessCard businessCard = new BusinessCard();
        businessCard.setId(dto.getId());
        businessCard.setTitle(dto.getTitle());
        businessCard.setFullName(dto.getFullName());
        businessCard.setWorkingTelephone(dto.getWorkingTelephone());

        return businessCard;
    }

    @Override
    public List<BusinessCard> toEntityList(List<BusinessCardDto> list) {
        return null;
    }
}
