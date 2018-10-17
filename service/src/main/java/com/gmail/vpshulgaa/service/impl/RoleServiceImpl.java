package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.enums.RolesEnum;
import com.gmail.vpshulgaa.service.RoleService;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    private final RoleDao roleDao;
    private final DtoConverter<RoleDto, Role> roleDtoConverter;

    @Autowired
    public RoleServiceImpl(
            RoleDao roleDao,
            @Qualifier("roleDtoConverter") DtoConverter<RoleDto, Role> roleDtoConverter) {
        this.roleDao = roleDao;
        this.roleDtoConverter = roleDtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        List<RoleDto> roles = new ArrayList<>();
        try {
            roles = roleDtoConverter.toDtoList(roleDao.findAll());
        } catch (Exception e) {
            logger.error("Failed to get users", e);
        }
        return roles;
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto findByName(RolesEnum name) {
        RoleDto roleDto = null;
        try {
            Role role = roleDao.findByName(name);
            roleDto = roleDtoConverter.toDto(role);
        } catch (Exception e) {
            logger.error("Failed to get role", e);
        }
        return roleDto;
    }
}
