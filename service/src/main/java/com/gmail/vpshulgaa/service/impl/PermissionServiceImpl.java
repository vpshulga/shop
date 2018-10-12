package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.PermissionDao;
import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.service.PermissionService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import java.util.ArrayList;
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
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);
    private final PermissionDao permissionDao;
    private final DtoConverter<PermissionDto, Permission> permissionDtoConverter;
    private final Converter<PermissionDto, Permission> permissionConverter;

    @Autowired
    public PermissionServiceImpl(
            PermissionDao permissionDao,
            @Qualifier("permissionDtoConverter") DtoConverter<PermissionDto, Permission> permissionDtoConverter,
            @Qualifier("permissionConverter") Converter<PermissionDto, Permission> permissionConverter) {
        this.permissionDao = permissionDao;
        this.permissionDtoConverter = permissionDtoConverter;
        this.permissionConverter = permissionConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionDto findOne(Long id) {
        PermissionDto permissionDto = null;
        try {
            Permission permission = permissionDao.findOne(id);
            permissionDto = permissionDtoConverter.toDto(permission);
        } catch (Exception e) {
            logger.error("Failed to get permission", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionDto> findAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public PermissionDto create(PermissionDto permissionDto) {
        try {
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.create(permission);
            permissionDto = permissionDtoConverter.toDto(permission);
        } catch (Exception e) {
            logger.error("Failed to save permission", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional
    public PermissionDto update(PermissionDto permissionDto) {
        try {
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.update(permission);
            permissionDto = permissionDtoConverter.toDto(permission);
        } catch (Exception e) {
            logger.error("Failed to update permission", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional
    public PermissionDto delete(PermissionDto permissionDto) {
        try {
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.delete(permission);
            permissionDto = permissionDtoConverter.toDto(permission);
        } catch (Exception e) {
            logger.error("Failed to delete permission", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            permissionDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete permission", e);
        }
    }
}
