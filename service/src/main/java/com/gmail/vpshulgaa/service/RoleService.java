package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.enums.RolesEnum;
import com.gmail.vpshulgaa.service.dto.RoleDto;
import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    RoleDto findByName(RolesEnum name);
}
