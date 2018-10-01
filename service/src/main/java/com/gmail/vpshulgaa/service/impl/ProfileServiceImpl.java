package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ProfileDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger logger = LogManager.getLogger(ProfileServiceImpl.class);

    private final ProfileDao profileDao;
    private final DtoConverter<ProfileDto, Profile> profileDtoConverter;
    private final Converter<ProfileDto, Profile> profileConverter;

    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao,
                              @Qualifier("profileDtoConverter") DtoConverter<ProfileDto, Profile> profileDtoConverter,
                              @Qualifier("profileConverter") Converter<ProfileDto, Profile> profileConverter) {
        this.profileDao = profileDao;
        this.profileDtoConverter = profileDtoConverter;
        this.profileConverter = profileConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto findOne(Long id) {
        ProfileDto profileDto = null;
        try {
            Profile profile = profileDao.findOne(id);
            profileDto = profileDtoConverter.toDto(profile);
        } catch (Exception e) {
            logger.error("Failed to get profile", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<ProfileDto> findAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto create(ProfileDto profileDto) {
        try {
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.create(profile);
            profileDto = profileDtoConverter.toDto(profile);
        } catch (Exception e) {
            logger.error("Failed to save profile", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto update(ProfileDto profileDto) {
        try {
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.update(profile);
            profileDto = profileDtoConverter.toDto(profile);
        } catch (Exception e) {
            logger.error("Failed to update profile", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ProfileDto delete(ProfileDto profileDto) {
        try {
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.delete(profile);
            profileDto = profileDtoConverter.toDto(profile);
        } catch (Exception e) {
            logger.error("Failed to delete profile", e);
        }
        return profileDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            profileDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete profile", e);
        }
    }
}
