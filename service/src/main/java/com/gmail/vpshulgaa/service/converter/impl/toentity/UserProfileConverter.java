package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userProfileConverter")
public class UserProfileConverter implements Converter<UserProfileDto, User> {

    private final Converter<RoleDto, Role> roleConverter;

    @Autowired
    public UserProfileConverter(Converter<RoleDto, Role> roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public User toEntity(UserProfileDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setDisabled(dto.getDisabled());
        user.setDeleted(dto.getDeleted());
        if (dto.getRole() != null) {
            Role role = roleConverter.toEntity(dto.getRole());
            user.setRole(role);
        }
        return user;
    }
}
