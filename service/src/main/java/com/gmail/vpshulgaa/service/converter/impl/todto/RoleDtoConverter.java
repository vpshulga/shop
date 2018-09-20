package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDtoConverter implements DtoConverter<RoleDto, Role> {
    private PermissionDtoConverter permissionDtoConverter = new PermissionDtoConverter();

    @Override
    public RoleDto toDto(Role entity) {
        if (entity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());

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
