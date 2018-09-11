package com.gmail.vpshulgaa.service.students.service.impl;

import com.gmail.vpshulgaa.dao.students.dao.UniversityDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.University;
import com.gmail.vpshulgaa.dao.students.dao.impl.UniversityDaoImpl;
import com.gmail.vpshulgaa.service.students.service.UniversityService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UniversityServiceImpl implements UniversityService {
    private static final Logger logger = LogManager.getLogger(UniversityServiceImpl.class);

    private UniversityDao universityDao = new UniversityDaoImpl(University.class);


    @Override
    public University findOne(Long id) {
        University university = null;
        Session session = universityDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            university = universityDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get university", e);
        }
        return university;
    }

    @Override
    public List<University> findAll() {
        return null;
    }

    @Override
    public void create(University university) {
        Session session = universityDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            universityDao.create(university);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save university", e);
        }
    }

    @Override
    public void update(University university) {
        Session session = universityDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            universityDao.update(university);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update university", e);
        }
    }

    @Override
    public void delete(University university) {
        Session session = universityDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            universityDao.delete(university);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete university", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = universityDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            universityDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete university", e);
        }
    }
}
