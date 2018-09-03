package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.PermissionDao;
import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.impl.PermissionDaoImpl;
import com.gmail.vpshulgaa.service.PermissionService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);

    private PermissionDao permissionDao = new PermissionDaoImpl(Permission.class);

    @Override
    public Permission findOne(Long id) {
        Permission user = null;
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            user = permissionDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get permission", e);
        }
        return user;
    }

    @Override
    public List<Permission> findAll() {
        return null;
    }

    @Override
    public void create(Permission permission) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            permissionDao.create(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save permission", e);
        }
    }

    @Override
    public void update(Permission permission) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            permissionDao.update(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update permission", e);
        }
    }

    @Override
    public void delete(Permission permission) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            permissionDao.delete(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete permission", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            permissionDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete permission", e);
        }
    }
}
