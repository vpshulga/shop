package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
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
        UserDtoConverter userDtoConverter = new UserDtoConverter();
        Set<UserDto> userDtoSet = new HashSet<>();
        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> list) {
        return null;
    }
}
