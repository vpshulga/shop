package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userDtoConverter")
public class UserDtoConverter implements DtoConverter<UserDto, User> {

    private final DtoConverter<ProfileDto, Profile> profileDtoConverter;
    private final DtoConverter<AuditDto, Audit> auditDtoConverter;
    private final DtoConverter<RoleDto, Role> roleDtoConverter;
    private final DtoConverter<DiscountDto, Discount> discountDtoConverter;

    @Autowired
    public UserDtoConverter(@Qualifier("profileDtoConverter") DtoConverter<ProfileDto, Profile> profileDtoConverter,
                            @Qualifier("auditDtoConverter") DtoConverter<AuditDto, Audit> auditDtoConverter,
                            @Qualifier("roleDtoConverter") DtoConverter<RoleDto, Role> roleDtoConverter,
                            @Qualifier("discountDtoConverter") DtoConverter<DiscountDto, Discount> discountDtoConverter) {
        this.profileDtoConverter = profileDtoConverter;
        this.auditDtoConverter = auditDtoConverter;
        this.roleDtoConverter = roleDtoConverter;
        this.discountDtoConverter = discountDtoConverter;
    }

    @Override
    public UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setName(entity.getName());
        userDto.setSurname(entity.getSurname());
        userDto.setPassword(entity.getPassword());
        userDto.setDisabled(entity.isDisabled());
        if (entity.getProfile() != null) {
            ProfileDto profileDto = profileDtoConverter.toDto(entity.getProfile());
            userDto.setProfile(profileDto);
        }

        Set<AuditDto> audits = new HashSet<>();
        for (Audit audit : entity.getAudits()) {
            audits.add(auditDtoConverter.toDto(audit));
        }
        userDto.setAudits(audits);

        if (entity.getRole() != null) {
            RoleDto roleDto = roleDtoConverter.toDto(entity.getRole());
            userDto.setRole(roleDto);
        }

        if (entity.getDiscount() != null) {
            DiscountDto discountDto = discountDtoConverter.toDto(entity.getDiscount());
            userDto.setDiscount(discountDto);
        }

        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> list) {
        List<UserDto> users = new ArrayList<>();
        for (User user : list) {
            users.add(toDto(user));
        }
        return users;
    }
}
