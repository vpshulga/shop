package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
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
        for (PermissionDto permissionDto : dto.getPermissionDtoSet()) {
            permissionDto.getRoleDtoSet().add(dto);
            for (RoleDto roleDto : permissionDto.getRoleDtoSet()) {
                roleDto.getPermissionDtoSet().add(permissionDto);
                Permission permission = permissionConverter.toEntity(permissionDto);
                role.getPermissions().add(permission);
                permission.getRoles().add(role);
            }


        }
        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> list) {
        return null;
    }
}
;