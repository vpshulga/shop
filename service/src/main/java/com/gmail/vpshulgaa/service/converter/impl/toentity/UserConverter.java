package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConverter implements Converter<UserDto, User> {

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

        ProfileConverter profileConverter = new ProfileConverter();
        if (dto.getProfileDto() != null) {
            Profile profile = profileConverter.toEntity(dto.getProfileDto());
            user.setProfile(profile);
            profile.setUser(user);
        }

        AuditConverter auditConverter = new AuditConverter();
        Set<Audit> audits = new HashSet<>();
        for (AuditDto auditDto : dto.getAudits()) {
            audits.add(auditConverter.toEntity(auditDto));
        }
        user.setAudits(audits);

        OrderConverter orderConverter = new OrderConverter();
        Set<Order> orders = new HashSet<>();
        for (OrderDto orderDto : dto.getOrders()) {
            orders.add(orderConverter.toEntity(orderDto));
        }
        user.setOrders(orders);

        RoleConverter roleConverter = new RoleConverter();
        if (dto.getRole() != null) {
            Role role = roleConverter.toEntity(dto.getRole());
            user.setRole(role);
        }

        DiscountConverter discountConverter = new DiscountConverter();
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
