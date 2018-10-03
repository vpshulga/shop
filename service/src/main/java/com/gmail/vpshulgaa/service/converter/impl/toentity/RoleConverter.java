package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("roleConverter")
public class RoleConverter implements Converter<RoleDto, Role> {
    private final Converter<PermissionDto, Permission> permissionConverter;

    @Autowired
    public RoleConverter(@Qualifier("permissionConverter") Converter<PermissionDto, Permission> permissionConverter) {
        this.permissionConverter = permissionConverter;
    }

    @Override
    public Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());

        Set<Permission> permissions = new HashSet<>();
        for (PermissionDto permissionDto : dto.getPermissions()) {
            permissions.add(permissionConverter.toEntity(permissionDto));
        }
        role.setPermissions(permissions);
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> list) {
        List<Role> roles = new ArrayList<>();
        for (RoleDto roleDto : list) {
            roles.add(toEntity(roleDto));
        }
        return roles;
    }
}