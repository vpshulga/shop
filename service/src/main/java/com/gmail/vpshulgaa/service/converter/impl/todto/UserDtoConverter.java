package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.*;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDtoConverter implements DtoConverter<UserDto, User> {
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
        ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
        if (entity.getProfile() != null){
            ProfileDto profileDto = profileDtoConverter.toDto(entity.getProfile());
            userDto.setProfileDto(profileDto);
        }

        NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
        Set<NewsDto> news = new HashSet<>();
        for (News n : entity.getNews()) {
            news.add(newsDtoConverter.toDto(n));
        }
        userDto.setNews(news);

        AuditDtoConverter auditDtoConverter = new AuditDtoConverter();
        Set<AuditDto> audits = new HashSet<>();
        for (Audit audit : entity.getAudits()) {
            audits.add(auditDtoConverter.toDto(audit));
        }
        userDto.setAudits(audits);

        OrderDtoConverter orderDtoConverter = new OrderDtoConverter();
        Set<OrderDto> orders = new HashSet<>();
        for (Order order : entity.getOrders()) {
            orders.add(orderDtoConverter.toDto(order));
        }
        userDto.setOrders(orders);

        RoleDtoConverter roleDtoConverter = new RoleDtoConverter();
        if (entity.getRole() !=  null) {
            RoleDto roleDto = roleDtoConverter.toDto(entity.getRole());
            userDto.setRole(roleDto);
        }

        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> list) {
        return null;
    }
}
