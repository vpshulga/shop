package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.AuditDao;
import com.gmail.vpshulgaa.dao.entities.Audit;
import com.gmail.vpshulgaa.dao.impl.AuditDaoImpl;
import com.gmail.vpshulgaa.service.AuditService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.AuditDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.AuditConverter;
import com.gmail.vpshulgaa.service.dto.AuditDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    private final AuditDao auditDao;
    private final Converter<AuditDto, Audit> auditConverter;
    private final DtoConverter<AuditDto, Audit> auditDtoConverter;

    @Autowired
    public AuditServiceImpl(AuditDao auditDao,
                            @Qualifier("auditConverter") Converter<AuditDto, Audit>  auditConverter,
                            @Qualifier("auditDtoConverter") DtoConverter<AuditDto, Audit>  auditDtoConverter) {
        this.auditDao = auditDao;
        this.auditConverter = auditConverter;
        this.auditDtoConverter = auditDtoConverter;
    }


    @Override
    public AuditDto findOne(Long id) {
        AuditDto auditDto = null;
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Audit audit = auditDao.findOne(id);
            auditDto = auditDtoConverter.toDto(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get audit", e);
        }
        return auditDto;
    }

    @Override
    public List<AuditDto> findAll() {
        return null;
    }

    @Override
    public AuditDto create(AuditDto auditDto) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.create(audit);
            auditDto = auditDtoConverter.toDto(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save audit", e);
        }
        return auditDto;
    }

    @Override
    public AuditDto update(AuditDto auditDto) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.update(audit);
            auditDto = auditDtoConverter.toDto(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update audit", e);
        }
        return auditDto;
    }

    @Override
    public AuditDto delete(AuditDto auditDto) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.delete(audit);
            auditDto = auditDtoConverter.toDto(audit);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete audit", e);
        }
        return auditDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
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
