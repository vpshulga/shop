package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import com.gmail.vpshulgaa.service.dto.UserDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.List;

public interface UserService {
    UserProfileDto findOne(final Long id);

    List<UserProfileDto> findAll();

    UserProfileDto create(final UserProfileDto dto);

    UserProfileDto update(final UserProfileDto dto);

    UserProfileDto delete(final UserProfileDto dto);

    void deleteById(final Long id);

    UserProfileDto findByEmail(String email);

    List<UserProfileDto> findNotDeletedUsers();

    UserProfileDto changePassword(ChangePasswordDto changePassword, UserProfileDto user);
}
