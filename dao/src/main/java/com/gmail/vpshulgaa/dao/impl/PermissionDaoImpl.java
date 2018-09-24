package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.PermissionDao;
import com.gmail.vpshulgaa.dao.entities.Permission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {
    private static final Logger logger = LogManager.getLogger(PermissionDaoImpl.class);

    public PermissionDaoImpl() {
        super(Permission.class);
    }
}
