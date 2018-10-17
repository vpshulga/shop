package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public Long countOfOrders() {
        String hql = "select count(*) from Order as o";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countOfOrdersForUser(Long userId) {
        String hql = "select count(*) from Order as o where o.user.id=:userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Order> findOrdersByPage(Long page, int maxResults) {
        String hql = "from Order as o order by o.created desc";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page - 1) * maxResults);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.list();
    }

    @Override
    public List<Order> findOrdersByPageForUser(Long page, int maxResults, Long userId) {
        String hql = "from Order as o where o.user.id=:userId order by o.created desc ";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page - 1) * maxResults);
        query.setParameter("userId", userId);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.list();
    }
}
