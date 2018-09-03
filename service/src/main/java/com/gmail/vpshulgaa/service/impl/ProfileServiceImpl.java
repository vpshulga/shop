package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.ProfileDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.impl.ProfileDaoImpl;
import com.gmail.vpshulgaa.service.ProfileService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProfileServiceImpl implements ProfileService {
    private static final Logger logger = LogManager.getLogger(ProfileServiceImpl.class);

    private ProfileDao profileDao = new ProfileDaoImpl(Profile.class);


    @Override
    public Profile findOne(Long id) {
        Profile profile = null;
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            profile = profileDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get profile", e);
        }
        return profile;
    }

    @Override
    public List<Profile> findAll() {
        return null;
    }

    @Override
    public void create(Profile profile) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            profileDao.create(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save profile", e);
        }
    }

    @Override
    public void update(Profile profile) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            profileDao.update(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update profile", e);
        }
    }

    @Override
    public void delete(Profile profile) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            profileDao.delete(profile);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete profile", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = profileDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
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
