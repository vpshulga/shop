package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter implements Converter<UserDto, User> {
    private final Converter<ProfileDto, Profile> profileConverter;
    private final Converter<AuditDto, Audit> auditConverter;
    private final Converter<RoleDto, Role> roleConverter;
    private final Converter<DiscountDto, Discount> discountConverter;

    @Autowired
    public UserConverter(@Qualifier("profileConverter") Converter<ProfileDto, Profile> profileConverter,
                         @Qualifier("auditConverter") Converter<AuditDto, Audit> auditConverter,
                         @Qualifier("roleConverter") Converter<RoleDto, Role> roleConverter,
                         @Qualifier("discountConverter") Converter<DiscountDto, Discount> discountConverter) {
        this.profileConverter = profileConverter;
        this.auditConverter = auditConverter;
        this.roleConverter = roleConverter;
        this.discountConverter = discountConverter;
    }

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
        user.setDisabled(dto.getDisabled());
        user.setDeleted(dto.getDeleted());

        if (dto.getProfile() != null) {
            Profile profile = profileConverter.toEntity(dto.getProfile());
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
        List<User> users = new ArrayList<>();
        for (UserDto userDto : list) {
            users.add(toEntity(userDto));
        }
        return users;
    }
}
