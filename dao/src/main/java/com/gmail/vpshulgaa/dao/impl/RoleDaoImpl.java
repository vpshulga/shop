package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.enums.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(Roles name) {
        String hql = "from Role as r where r.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);

        return (Role) query.uniqueResult();
    }
}
