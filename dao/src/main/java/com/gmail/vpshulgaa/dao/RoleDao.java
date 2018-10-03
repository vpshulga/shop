package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.enums.Roles;

public interface RoleDao extends GenericDao<Role>  {
    Role findByName(Roles name);
}
