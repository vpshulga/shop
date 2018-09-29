package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.impl.RoleDaoImpl;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.todto.RoleDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.RoleConverter;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleDao roleDao;
    private final DtoConverter<RoleDto, Role> roleDtoConverter;
    private final Converter<RoleDto, Role> roleConverter;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao,
                           @Qualifier("roleDtoConverter") DtoConverter<RoleDto, Role> roleDtoConverter,
                           @Qualifier("roleConverter") Converter<RoleDto, Role> roleConverter) {
        this.roleDao = roleDao;
        this.roleDtoConverter = roleDtoConverter;
        this.roleConverter = roleConverter;
    }

    @Override
    public RoleDto findOne(Long id) {
        RoleDto roleDto = null;
        try {
            Role role = roleDao.findOne(id);
            roleDto = roleDtoConverter.toDto(role);
        } catch (Exception e) {
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
        try {
            Role role = roleConverter.toEntity(roleDto);
            roleDao.create(role);
            roleDto = roleDtoConverter.toDto(role);
        } catch (Exception e) {
            logger.error("Failed to save role", e);
        }
        return roleDto;
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        try {
            Role role = roleConverter.toEntity(roleDto);
            roleDao.update(role);
            roleDto = roleDtoConverter.toDto(role);
        } catch (Exception e) {
            logger.error("Failed to update role", e);
        }
        return roleDto;
    }

    @Override
    public RoleDto delete(RoleDto roleDto) {
        try {
            Role role = roleConverter.toEntity(roleDto);
            roleDao.delete(role);
            roleDto = roleDtoConverter.toDto(role);
        } catch (Exception e) {
            logger.error("Failed to delete role", e);
        }
        return roleDto;
    }

    @Override
    public void deleteById(Long id) {
        try {
            roleDao.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete role", e);
        }
    }
}
