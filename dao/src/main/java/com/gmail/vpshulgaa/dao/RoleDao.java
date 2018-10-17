package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.enums.RolesEnum;

public interface RoleDao extends GenericDao<Role> {

    Role findByName(RolesEnum name);
}
