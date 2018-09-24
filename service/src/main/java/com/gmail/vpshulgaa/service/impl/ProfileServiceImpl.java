package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ProfileDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.impl.ProfileDaoImpl;
import com.gmail.vpshulgaa.service.ProfileService;
import com.gmail.vpshulgaa.service.converter.impl.todto.ProfileDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.ProfileConverter;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger logger = LogManager.getLogger(ProfileServiceImpl.class);

    private ProfileDao profileDao = new ProfileDaoImpl();
    private ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
    private ProfileConverter profileConverter = new ProfileConverter();

    @Override
    public ProfileDto findOne(Long id) {
        ProfileDto profileDto = null;
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Profile profile = profileDao.findOne(id);
            profileDto = profileDtoConverter.toDto(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get profile", e);
        }
        return profileDto;
    }

    @Override
    public List<ProfileDto> findAll() {
        return null;
    }

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.create(profile);
            profileDto = profileDtoConverter.toDto(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save profile", e);
        }
        return profileDto;
    }

    @Override
    public ProfileDto update(ProfileDto profileDto) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.update(profile);
            profileDto = profileDtoConverter.toDto(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update profile", e);
        }
        return profileDto;
    }

    @Override
    public ProfileDto delete(ProfileDto profileDto) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Profile profile = profileConverter.toEntity(profileDto);
            profileDao.delete(profile);
            profileDto = profileDtoConverter.toDto(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete profile", e);
        }
        return profileDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            profileDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete profile", e);
        }
    }
}
