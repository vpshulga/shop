package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.AuditDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserConverter implements Converter<UserDto, User> {
    private ProfileConverter profileConverter = new ProfileConverter();
    private AuditConverter auditConverter = new AuditConverter();
    private RoleConverter roleConverter = new RoleConverter();
    private DiscountConverter discountConverter = new DiscountConverter();

    @Override
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());

        if (dto.getProfileDto() != null) {
            Profile profile = profileConverter.toEntity(dto.getProfileDto());
            user.setProfile(profile);
            profile.setUser(user);
        }
        Set<Audit> audits = new HashSet<>();
        for (AuditDto auditDto : dto.getAudits()) {
            audits.add(auditConverter.toEntity(auditDto));
        }
        user.setAudits(audits);

        if (dto.getRole() != null) {
            Role role = roleConverter.toEntity(dto.getRole());
            user.setRole(role);
        }

        if (dto.getDiscount() != null) {
            Discount discount = discountConverter.toEntity(dto.getDiscount());
            user.setDiscount(discount);
        }

        return user;
    }


    @Override
    public List<User> toEntityList(List<UserDto> list) {
        return null;
    }
}
