package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleConverter implements Converter<RoleDto, Role> {
    @Override
    public Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());

        PermissionConverter permissionConverter = new PermissionConverter();
        Set<Permission> permissions = new HashSet<>();
        for (PermissionDto permissionDto : dto.getPermissions()) {
            permissions.add(permissionConverter.toEntity(permissionDto));
        }
        role.setPermissions(permissions);
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> list) {
        return null;
    }
}
;