package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Audit;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDtoConverter implements DtoConverter<UserDto, User> {

    private ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
    private AuditDtoConverter auditDtoConverter = new AuditDtoConverter();
    private RoleDtoConverter roleDtoConverter = new RoleDtoConverter();
    private DiscountDtoConverter discountDtoConverter = new DiscountDtoConverter();

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
        if (entity.getProfile() != null) {
            ProfileDto profileDto = profileDtoConverter.toDto(entity.getProfile());
            userDto.setProfileDto(profileDto);
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
        return null;
    }
}
