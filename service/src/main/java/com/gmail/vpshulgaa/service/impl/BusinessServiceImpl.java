package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.BusinessCardDao;
import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.BusinessCard;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.BusinessService;
import com.gmail.vpshulgaa.service.UserService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.BusinessCardDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    private static final Logger logger = LogManager.getLogger(BusinessServiceImpl.class);
    private final BusinessCardDao businessCardDao;
    private final UserDao userDao;
    private final Converter<BusinessCardDto, BusinessCard> businessCardConverter;
    private final DtoConverter<BusinessCardDto, BusinessCard> businessCardDtoConverter;
    private final UserService userService;
    private final Converter<UserProfileDto, User> userProfileConverter;

    @Autowired
    public BusinessServiceImpl(
            BusinessCardDao businessCardDao,
            @Qualifier("businessCardConverter") Converter<BusinessCardDto, BusinessCard> businessCardConverter,
            @Qualifier("businessCardDtoConverter") DtoConverter<BusinessCardDto, BusinessCard> businessCardDtoConverter,
            UserService userService,
            Converter<UserProfileDto, User> userProfileConverter, UserDao userDao) {
        this.businessCardDao = businessCardDao;
        this.businessCardConverter = businessCardConverter;
        this.businessCardDtoConverter = businessCardDtoConverter;
        this.userService = userService;
        this.userProfileConverter = userProfileConverter;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public BusinessCardDto findOne(Long id) {
        BusinessCardDto commentDto = null;
        try {
            BusinessCard businessCard = businessCardDao.findOne(id);
            commentDto = businessCardDtoConverter.toDto(businessCard);
        } catch (Exception e) {
            logger.error("Failed to get cards", e);
        }
        return commentDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessCardDto> findAll() {
        List<BusinessCardDto> businessCards = new ArrayList<>();
        try {
            businessCards = businessCardDtoConverter.toDtoList(businessCardDao.findAll());
        } catch (Exception e) {
            logger.error("Failed to get cards", e);
        }
        return businessCards;
    }

    @Override
    @Transactional
    public BusinessCardDto create(BusinessCardDto dto) {
        try {
            BusinessCard businessCard = businessCardConverter.toEntity(dto);
            Long userId = ServiceUtils.getPrincipal().getId();
            User user = userDao.findOne(userId);
            businessCard.setUser(user);
            businessCardDao.create(businessCard);
            dto = businessCardDtoConverter.toDto(businessCard);
        } catch (Exception e) {
            logger.error("Failed to save card", e);
        }
        return dto;
    }

    @Override
    @Transactional
    public BusinessCardDto update(BusinessCardDto dto, Long userId) {
        try {
            BusinessCard businessCard = businessCardConverter.toEntity(dto);
            User user = userDao.findOne(userId);
            businessCard.setUser(user);
            businessCardDao.update(businessCard);
            dto = businessCardDtoConverter.toDto(businessCard);
        } catch (Exception e) {
            logger.error("Failed to update card", e);
        }
        return dto;
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
        try {
            businessCardDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete news", e);
        }
    }

    @Override
    @Transactional
    public List<BusinessCardDto> findCardsForUser() {
        List<BusinessCardDto> businessCardDtos = new ArrayList<>();
        List<BusinessCard> businessCards;
        try {
            Long userId = ServiceUtils.getPrincipal().getId();
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
