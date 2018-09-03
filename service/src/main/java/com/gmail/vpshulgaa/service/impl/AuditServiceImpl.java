package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.AuditDao;
import com.gmail.vpshulgaa.dao.entities.Audit;
import com.gmail.vpshulgaa.dao.impl.AuditDaoImpl;
import com.gmail.vpshulgaa.service.AuditService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuditServiceImpl implements AuditService{
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);

    private AuditDao auditDao = new AuditDaoImpl(Audit.class);

    @Override
    public Audit findOne(Long id) {
        Audit audit = null;
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            audit = auditDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get audit", e);
        }
        return audit;
    }

    @Override
    public List<Audit> findAll() {
        return null;
    }

    @Override
    public void create(Audit audit) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            auditDao.create(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save audit", e);
        }
    }

    @Override
    public void update(Audit audit) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            auditDao.update(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update audit", e);
        }
    }

    @Override
    public void delete(Audit audit) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            auditDao.delete(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete audit", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            auditDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete audit", e);
        }
    }
}
