package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("permissionConverter")
public class PermissionConverter implements Converter<PermissionDto, Permission> {
    @Override
    public Permission toEntity(PermissionDto dto) {
        if (dto == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());

        return permission;
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDto> list) {
        return null;
    }
}
