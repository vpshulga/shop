package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.BusinessCardDto;

import java.util.List;

public interface BusinessService {
    BusinessCardDto findOne(final Long id);

    List<BusinessCardDto> findAll();

    BusinessCardDto create(final BusinessCardDto dto);

    BusinessCardDto update(final BusinessCardDto dto, Long userId);

    BusinessCardDto delete(final BusinessCardDto dto);

    void deleteById(final Long id);

    List<BusinessCardDto> findCardsForUser();
}
