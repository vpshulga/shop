package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.GenericDao;
import com.gmail.vpshulgaa.dao.entities.User;
import com.gmail.vpshulgaa.service.dto.UserDto;
import java.util.List;

public interface UserService extends GenericService<UserDto>{
    UserDto findByEmail(String email);
}
