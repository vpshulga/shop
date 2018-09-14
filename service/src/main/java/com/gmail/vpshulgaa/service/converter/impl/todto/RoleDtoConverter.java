package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDtoConverter implements DtoConverter<RoleDto, Role> {
    @Override
    public RoleDto toDto(Role entity) {
        if (entity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());

        PermissionDtoConverter permissionDtoConverter = new PermissionDtoConverter();
        Set<PermissionDto> permissions = new HashSet<>();
        for (Permission permission : entity.getPermissions()) {
            permissions.add(permissionDtoConverter.toDto(permission));
        }
        roleDto.setPermissions(permissions);

        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> list) {
        return null;
    }
}
