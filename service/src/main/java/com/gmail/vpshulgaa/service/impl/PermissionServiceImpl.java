package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.PermissionDao;
import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.impl.PermissionDaoImpl;
import com.gmail.vpshulgaa.service.PermissionService;
import com.gmail.vpshulgaa.service.converter.impl.todto.PermissionDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.PermissionConverter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);

    private PermissionDao permissionDao = new PermissionDaoImpl(Permission.class);
    private PermissionDtoConverter permissionDtoConverter = new PermissionDtoConverter();
    private PermissionConverter permissionConverter = new PermissionConverter();

    @Override
    public PermissionDto findOne(Long id) {
        PermissionDto permissionDto = null;
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Permission permission = permissionDao.findOne(id);
            permissionDto = permissionDtoConverter.toDto(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get permission", e);
        }
        return permissionDto;
    }

    @Override
    public List<PermissionDto> findAll() {
        return null;
    }

    @Override
    public PermissionDto create(PermissionDto permissionDto) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.create(permission);
            permissionDto = permissionDtoConverter.toDto(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save permission", e);
        }
        return permissionDto;
    }

    @Override
    public PermissionDto update(PermissionDto permissionDto) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.update(permission);
            permissionDto = permissionDtoConverter.toDto(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update permission", e);
        }
        return permissionDto;
    }

    @Override
    public PermissionDto delete(PermissionDto permissionDto) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.delete(permission);
            permissionDto = permissionDtoConverter.toDto(permission);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete permission", e);
        }
        return permissionDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
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
