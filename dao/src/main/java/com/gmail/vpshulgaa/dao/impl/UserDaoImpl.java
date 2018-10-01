package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.UserDao;
import com.gmail.vpshulgaa.dao.entities.User;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "from User as U where U.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);

        return (User) query.uniqueResult();
    }

    @Override
    public List<User> findEnabledUsers() {
        String hql = "from User as u where u.isDisabled=false";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }
}
