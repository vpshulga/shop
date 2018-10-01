package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;

public interface UserService extends GenericService<UserDto> {
    UserDto findByEmail(String email);

    List<UserDto> findEnabledUsers();
}
