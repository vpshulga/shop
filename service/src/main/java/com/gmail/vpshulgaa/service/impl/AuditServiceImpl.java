package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.AuditDao;
import com.gmail.vpshulgaa.dao.entities.Audit;
import com.gmail.vpshulgaa.service.AuditService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.AuditDto;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditServiceImpl implements AuditService {
    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);
    private final AuditDao auditDao;
    private final Converter<AuditDto, Audit> auditConverter;
    private final DtoConverter<AuditDto, Audit> auditDtoConverter;

    @Autowired
    public AuditServiceImpl(AuditDao auditDao,
                            @Qualifier("auditConverter") Converter<AuditDto, Audit> auditConverter,
                            @Qualifier("auditDtoConverter") DtoConverter<AuditDto, Audit> auditDtoConverter) {
        this.auditDao = auditDao;
        this.auditConverter = auditConverter;
        this.auditDtoConverter = auditDtoConverter;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public AuditDto findOne(Long id) {
        AuditDto auditDto = null;
        try {
            Audit audit = auditDao.findOne(id);
            auditDto = auditDtoConverter.toDto(audit);
        } catch (Exception e) {
            logger.error("Failed to get audit", e);
        }
        return auditDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<AuditDto> findAll() {
        return null;
    }

    @Override
    public AuditDto create(AuditDto auditDto) {
        try {
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.create(audit);
            auditDto = auditDtoConverter.toDto(audit);
        } catch (Exception e) {
            logger.error("Failed to save audit", e);
        }
        return auditDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public AuditDto update(AuditDto auditDto) {
        try {
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.update(audit);
            auditDto = auditDtoConverter.toDto(audit);
        } catch (Exception e) {
            logger.error("Failed to update audit", e);
        }
        return auditDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public AuditDto delete(AuditDto auditDto) {
        try {
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.delete(audit);
            auditDto = auditDtoConverter.toDto(audit);
        } catch (Exception e) {
            logger.error("Failed to delete audit", e);
        }
        return auditDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            auditDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete audit", e);
        }
    }
}
