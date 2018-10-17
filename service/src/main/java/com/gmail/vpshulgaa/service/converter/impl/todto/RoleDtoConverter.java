package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("roleDtoConverter")
public class RoleDtoConverter implements DtoConverter<RoleDto, Role> {

    @Override
    public RoleDto toDto(Role entity) {
        if (entity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());

        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> list) {
        List<RoleDto> roles = new ArrayList<>();
        for (Role role : list) {
            roles.add(toDto(role));
        }
        return roles;
    }
}
