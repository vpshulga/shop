package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.enums.Roles;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import java.util.List;

public interface RoleService {
    RoleDto findOne(final Long id);

    List<RoleDto> findAll();

    RoleDto create(final RoleDto dto);

    RoleDto update(final RoleDto dto);

    RoleDto delete(final RoleDto dto);

    void deleteById(final Long id);

    RoleDto findByName(Roles name);
}
