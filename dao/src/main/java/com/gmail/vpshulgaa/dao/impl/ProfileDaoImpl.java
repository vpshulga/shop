package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.ProfileDao;
import com.gmail.vpshulgaa.dao.entities.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {
    private static final Logger logger = LogManager.getLogger(ProfileDaoImpl.class);

    public ProfileDaoImpl(Class<Profile> clazz) {
        super(clazz);
    }
}
