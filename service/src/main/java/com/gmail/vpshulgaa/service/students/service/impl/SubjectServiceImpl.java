package com.gmail.vpshulgaa.service.students.service.impl;

import com.gmail.vpshulgaa.dao.students.dao.SubjectDao;
import com.gmail.vpshulgaa.dao.students.dao.entities.Subject;
import com.gmail.vpshulgaa.dao.students.dao.impl.SubjectDaoImpl;
import com.gmail.vpshulgaa.service.students.service.SubjectService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SubjectServiceImpl implements SubjectService{
    private static final Logger logger = LogManager.getLogger(SubjectServiceImpl.class);

    private SubjectDao subjectDao = new SubjectDaoImpl(Subject.class);

    @Override
    public Subject findOne(Long id) {
        Subject subject = null;
        Session session = subjectDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            subject = subjectDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get subject", e);
        }
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public void create(Subject subject) {
        Session session = subjectDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            subjectDao.create(subject);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save subject", e);
        }
    }

    @Override
    public void update(Subject subject) {
        Session session = subjectDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            subjectDao.update(subject);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update subject", e);
        }
    }

    @Override
    public void delete(Subject subject) {
        Session session = subjectDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            subjectDao.delete(subject);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete subject", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = subjectDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            subjectDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete subject", e);
        }
    }
}
