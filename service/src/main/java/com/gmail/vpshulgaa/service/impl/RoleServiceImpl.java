package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.impl.RoleDaoImpl;
import com.gmail.vpshulgaa.service.RoleService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleServiceImpl implements RoleService{
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private RoleDao roleDao = new RoleDaoImpl(Role.class);

    @Override
    public Role findOne(Long id) {
        Role role = null;
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            role = roleDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get role", e);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public void create(Role role) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            roleDao.create(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save role", e);
        }
    }

    @Override
    public void update(Role role) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            roleDao.update(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update role", e);
        }
    }

    @Override
    public void delete(Role role) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            roleDao.delete(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete role", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            roleDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete role", e);
        }
    }
}
