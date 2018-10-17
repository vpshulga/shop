package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.BusinessCard;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("businessCardDtoConverter")
public class BusinessServiceDtoConverter implements DtoConverter<BusinessCardDto, BusinessCard> {

    @Override
    public BusinessCardDto toDto(BusinessCard entity) {
        if (entity == null) {
            return null;
        }
        BusinessCardDto businessCardDto = new BusinessCardDto();
        businessCardDto.setId(entity.getId());
        businessCardDto.setTitle(entity.getTitle());
        businessCardDto.setFullName(entity.getFullName());
        businessCardDto.setWorkingTelephone(entity.getWorkingTelephone());

        return businessCardDto;
    }

    @Override
    public List<BusinessCardDto> toDtoList(List<BusinessCard> list) {
        List<BusinessCardDto> businessCardDtos = new ArrayList<>();
        for (BusinessCard businessCard : list) {
            businessCardDtos.add(toDto(businessCard));
        }
        return businessCardDtos;
    }
}
