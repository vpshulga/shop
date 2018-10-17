package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.RoleDao;
import com.gmail.vpshulgaa.dao.entities.Role;
import com.gmail.vpshulgaa.dao.enums.RolesEnum;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(RolesEnum name) {
        String hql = "from Role as r where r.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);

        return (Role) query.uniqueResult();
    }
}
