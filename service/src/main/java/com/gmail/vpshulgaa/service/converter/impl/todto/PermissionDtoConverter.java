package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Permission;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.PermissionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("permissionDtoConverter")
public class PermissionDtoConverter implements DtoConverter<PermissionDto, Permission> {
    @Override
    public PermissionDto toDto(Permission entity) {
        if (entity == null) {
            return null;
        }
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(entity.getId());
        permissionDto.setName(entity.getName());
        return permissionDto;
    }

    @Override
    public List<PermissionDto> toDtoList(List<Permission> list) {
        return null;
    }
}
