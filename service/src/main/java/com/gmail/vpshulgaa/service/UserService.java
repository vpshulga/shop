package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.ChangePasswordDto;
import com.gmail.vpshulgaa.service.dto.UserProfileDto;
import java.util.List;

public interface UserService {

    UserProfileDto findOne(final Long id);

    UserProfileDto create(final UserProfileDto dto);

    UserProfileDto update(final UserProfileDto dto);

    UserProfileDto delete(final UserProfileDto dto);

    void deleteById(final Long id);

    UserProfileDto changePassword(ChangePasswordDto changePassword, Long userId);

    Long countOfUsers();

    List<UserProfileDto> findUsersByPage(Long page, int maxResults);

    boolean isExistsEmail(String email);
}
