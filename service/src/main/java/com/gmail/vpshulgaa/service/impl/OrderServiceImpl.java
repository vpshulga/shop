package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.impl.OrderDaoImpl;
import com.gmail.vpshulgaa.service.OrderService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderServiceImpl implements OrderService{
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao = new OrderDaoImpl(Order.class);

    @Override
    public Order findOne(Long id) {
        Order order = null;
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            order = orderDao.findOne(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get order", e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void create(Order order) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            orderDao.create(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save order", e);
        }
    }

    @Override
    public void update(Order order) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            orderDao.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update order", e);
        }
    }

    @Override
    public void delete(Order order) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            orderDao.delete(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete order", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            orderDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete order", e);
        }
    }
}
