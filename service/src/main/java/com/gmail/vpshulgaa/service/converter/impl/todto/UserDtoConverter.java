package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;

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
        userDto.setRoleId(entity.getRoleId());
        ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
        if (entity.getProfile() != null){
            ProfileDto profileDto = profileDtoConverter.toDto(entity.getProfile());
            userDto.setProfileDto(profileDto);
        }
//        userDto.setProfile(entity.getProfile());
//        userDto.setNews(entity.getNews());
        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> list) {
        return null;
    }
}
