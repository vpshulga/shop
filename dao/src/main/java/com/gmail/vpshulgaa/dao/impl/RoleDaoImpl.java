package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }
}
