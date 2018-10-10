package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("userProfileDtoConverter")
public class UserProfileDtoConverter implements DtoConverter<UserProfileDto, User> {
    @Override
    public UserProfileDto toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(entity.getId());
        userProfileDto.setName(entity.getName());
        userProfileDto.setEmail(entity.getEmail());
        userProfileDto.setSurname(entity.getSurname());
        userProfileDto.setPassword(entity.getPassword());
        userProfileDto.setDisabled(entity.getDisabled());
        userProfileDto.setDeleted(entity.getDeleted());
        userProfileDto.setAddress(entity.getProfile().getAddress());
        userProfileDto.setTelephone(entity.getProfile().getTelephone());

        return userProfileDto;
    }

    @Override
    public List<UserProfileDto> toDtoList(List<User> list) {
        return null;
    }
}
