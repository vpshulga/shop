package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.DiscountDto;
import java.util.List;

public interface DiscountService{
    DiscountDto findOne(final Long id);

    List<DiscountDto> findAll();

    DiscountDto create(final DiscountDto dto);

    DiscountDto update(final DiscountDto dto);

    DiscountDto delete(final DiscountDto dto);

    void deleteById(final Long id);
}
