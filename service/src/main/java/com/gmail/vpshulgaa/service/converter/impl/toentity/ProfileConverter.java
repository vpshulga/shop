package com.gmail.vpshulgaa.service.converter.impl.toentity;

import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.service.converter.Converter;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import org.springframework.stereotype.Component;

@Component("profileConverter")
public class ProfileConverter implements Converter<ProfileDto, Profile> {

    @Override
    public Profile toEntity(ProfileDto dto) {
        if (dto == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setUserId(dto.getUserId());
        profile.setAddress(dto.getAddress());
        profile.setTelephone(dto.getTelephone());

        return profile;
    }
}
