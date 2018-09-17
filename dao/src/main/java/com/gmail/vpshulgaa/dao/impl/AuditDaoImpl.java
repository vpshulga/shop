package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.AuditDao;
import com.gmail.vpshulgaa.dao.entities.Audit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {
    private static final Logger logger = LogManager.getLogger(AuditDaoImpl.class);

    public AuditDaoImpl(Class<Audit> clazz) {
        super(clazz);
    }
}
