package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.impl.RoleDaoImpl;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.converter.impl.todto.RoleDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.RoleConverter;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleServiceImpl implements RoleService{
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private RoleDtoConverter roleDtoConverter = new RoleDtoConverter();
    private RoleConverter roleConverter = new RoleConverter();

    @Override
    public RoleDto findOne(Long id) {
        RoleDto roleDto = null;
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Role role = roleDao.findOne(id);
            roleDto = roleDtoConverter.toDto(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get role", e);
        }
        return roleDto;
    }

    @Override
    public List<RoleDto> findAll() {
        return null;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Role role = roleConverter.toEntity(roleDto);
            roleDao.create(role);
            roleDto = roleDtoConverter.toDto(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save role", e);
        }
        return roleDto;
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Role role = roleConverter.toEntity(roleDto);
            roleDao.update(role);
            roleDto = roleDtoConverter.toDto(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update role", e);
        }
        return roleDto;
    }

    @Override
    public RoleDto delete(RoleDto roleDto) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Role role = roleConverter.toEntity(roleDto);
            roleDao.delete(role);
            roleDto = roleDtoConverter.toDto(role);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete role", e);
        }
        return roleDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
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
