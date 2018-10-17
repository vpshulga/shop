package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.AuditDao;
import com.gmail.vpshulgaa.dao.entities.Audit;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl() {
        super(Audit.class);
    }
}
