package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.UserDto;

public interface UserService extends GenericService<UserDto> {
    UserDto findByEmail(String email);
}
