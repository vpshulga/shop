package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("userProfileConverter")
public class UserProfileConverter implements Converter<UserProfileDto, User> {
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
        Profile profile = new Profile();
        profile.setAddress(dto.getAddress());
        profile.setUserId(dto.getId());
        profile.setTelephone(dto.getTelephone());
        user.setProfile(profile);
        profile.setUser(user);
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserProfileDto> list) {
        return null;
    }
}
