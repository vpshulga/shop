package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.BusinessCardDao;
import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.BusinessCard;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.BusinessService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import com.gmail.vpshulgaa.service.exception.EntityNotFoundException;
import com.gmail.vpshulgaa.service.util.CurrentUserUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusinessServiceImpl implements BusinessService {

    private static final Logger logger = LogManager.getLogger(BusinessServiceImpl.class);
    private final BusinessCardDao businessCardDao;
    private final UserDao userDao;
    private final Converter<BusinessCardDto, BusinessCard> businessCardConverter;
    private final DtoConverter<BusinessCardDto, BusinessCard> businessCardDtoConverter;

    @Autowired
    public BusinessServiceImpl(
            BusinessCardDao businessCardDao,
            @Qualifier("businessCardConverter") Converter<BusinessCardDto, BusinessCard> businessCardConverter,
            @Qualifier("businessCardDtoConverter") DtoConverter<BusinessCardDto, BusinessCard> businessCardDtoConverter,
            UserDao userDao) {
        this.businessCardDao = businessCardDao;
        this.businessCardConverter = businessCardConverter;
        this.businessCardDtoConverter = businessCardDtoConverter;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessCardDto findOne(Long id) {
        BusinessCard businessCard = businessCardDao.findOne(id);
        if (businessCard != null) {
            return businessCardDtoConverter.toDto(businessCard);
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    @Transactional
    public BusinessCardDto create(BusinessCardDto dto) {
        BusinessCard businessCard = businessCardConverter.toEntity(dto);
        Long userId = CurrentUserUtils.getPrincipal().getId();
        User user = userDao.findOne(userId);
        if (user != null) {
            businessCard.setUser(user);
            businessCardDao.create(businessCard);
            return businessCardDtoConverter.toDto(businessCard);
        } else {
            throw new EntityNotFoundException(User.class, userId);
        }

    }

    @Override
    @Transactional
    public BusinessCardDto delete(BusinessCardDto dto) {
        try {
            BusinessCard businessCard = businessCardConverter.toEntity(dto);
            businessCardDao.delete(businessCard);
            dto = businessCardDtoConverter.toDto(businessCard);
        } catch (Exception e) {
            logger.error("Failed to delete card", e);
        }
        return dto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (businessCardDao.findOne(id) != null) {
            businessCardDao.deleteById(id);
        } else {
            throw new EntityNotFoundException(BusinessCard.class, id);
        }
    }

    @Override
    @Transactional
    public List<BusinessCardDto> findCardsForUser() {
        List<BusinessCardDto> businessCardDtos = new ArrayList<>();
        List<BusinessCard> businessCards;
        try {
            Long userId = CurrentUserUtils.getPrincipal().getId();
            businessCards = businessCardDao.findCardsForUser(userId);
            for (BusinessCard businessCard : businessCards) {
                businessCardDtos.add(businessCardDtoConverter.toDto(businessCard));
            }
        } catch (Exception e) {
            logger.error("Failed to find cards", e);
        }
        return businessCardDtos;
    }
}
