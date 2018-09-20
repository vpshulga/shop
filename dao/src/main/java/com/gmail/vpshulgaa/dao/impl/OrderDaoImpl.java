package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    @Override
    public List<Order> findordersByUserId(Long userId) {
        String hql = "from Order as o Where o.user.id=:userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }
}
