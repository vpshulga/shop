package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ProfileDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import com.gmail.vpshulgaa.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final DtoConverter<ProfileDto, Profile> profileDtoConverter;

    @Autowired
    public ProfileServiceImpl(
            ProfileDao profileDao,
            @Qualifier("profileDtoConverter") DtoConverter<ProfileDto, Profile> profileDtoConverter) {
        this.profileDao = profileDao;
        this.profileDtoConverter = profileDtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto findOne(Long id) {
        Profile profile = profileDao.findOne(id);
        if (profile != null) {
            return profileDtoConverter.toDto(profile);
        } else {
            throw new EntityNotFoundException(Profile.class, id);
        }
    }
}
