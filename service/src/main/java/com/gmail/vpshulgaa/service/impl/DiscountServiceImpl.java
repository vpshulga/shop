package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.DiscountDao;
import com.gmail.vpshulgaa.dao.entities.Discount;
import com.gmail.vpshulgaa.service.DiscountService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.DiscountDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao;
    private final Converter<DiscountDto, Discount> discountConverter;
    private final DtoConverter<DiscountDto, Discount> discountDtoConverter;

    @Autowired
    public DiscountServiceImpl(
            DiscountDao discountDao,
            @Qualifier("discountConverter") Converter<DiscountDto, Discount> discountConverter,
            @Qualifier("discountDtoConverter") DtoConverter<DiscountDto, Discount> discountDtoConverter) {
        this.discountDao = discountDao;
        this.discountConverter = discountConverter;
        this.discountDtoConverter = discountDtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public DiscountDto findOne(Long id) {
        DiscountDto discountDto = null;
        try {
            Discount discount = discountDao.findOne(id);
            discountDto = discountDtoConverter.toDto(discount);
        } catch (Exception e) {
            logger.error("Failed to get discount", e);
        }
        return discountDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiscountDto> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public DiscountDto create(DiscountDto discountDto) {
        try {
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.create(discount);
            discountDto = discountDtoConverter.toDto(discount);
        } catch (Exception e) {
            logger.error("Failed to save discount", e);
        }
        return discountDto;
    }

    @Override
    @Transactional
    public DiscountDto update(DiscountDto discountDto) {
        try {
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.update(discount);
            discountDto = discountDtoConverter.toDto(discount);
        } catch (Exception e) {
            logger.error("Failed to update discount", e);
        }
        return discountDto;
    }

    @Override
    @Transactional
    public DiscountDto delete(DiscountDto discountDto) {
        try {
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.delete(discount);
            discountDto = discountDtoConverter.toDto(discount);
        } catch (Exception e) {
            logger.error("Failed to delete discount", e);
        }
        return discountDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            discountDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete discount", e);
        }
    }
}
