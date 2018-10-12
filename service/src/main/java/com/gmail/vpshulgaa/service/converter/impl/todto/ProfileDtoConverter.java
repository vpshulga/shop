package com.gmail.vpshulgaa.service.converter.impl.todto;

import com.gmail.vpshulgaa.dao.entities.Profile;
import com.gmail.vpshulgaa.service.converter.DtoConverter;
import com.gmail.vpshulgaa.service.dto.ProfileDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("profileDtoConverter")
public class ProfileDtoConverter implements DtoConverter<ProfileDto, Profile> {
    @Override
    public ProfileDto toDto(Profile entity) {
        if (entity == null) {
            return null;
        }
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserId(entity.getUserId());
        profileDto.setAddress(entity.getAddress());
        profileDto.setTelephone(entity.getTelephone());
        return profileDto;
    }

    @Override
    public List<ProfileDto> toDtoList(List<Profile> list) {
        return null;
    }
}
