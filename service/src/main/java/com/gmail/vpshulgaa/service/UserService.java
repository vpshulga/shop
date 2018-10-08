package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.UserDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.List;

public interface UserService {
    UserDto findOne(final Long id);

    List<UserDto> findAll();

    UserProfileDto create(final UserProfileDto dto);

    UserDto update(final UserDto dto);

    UserDto delete(final UserDto dto);

    void deleteById(final Long id);

    UserDto findByEmail(String email);

    List<UserDto> findEnabledUsers();

    UserProfileDto findUserProfile(final Long id);
}
