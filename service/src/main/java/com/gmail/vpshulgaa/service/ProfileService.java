package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.ProfileDto;
import java.util.List;

public interface ProfileService {
    ProfileDto findOne(final Long id);

    List<ProfileDto> findAll();

    ProfileDto create(final ProfileDto dto);

    ProfileDto update(final ProfileDto dto);

    ProfileDto delete(final ProfileDto dto);

    void deleteById(final Long id);
}
