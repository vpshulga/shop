package com.gmail.vpshulgaa.service.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import com.gmail.vpshulgaa.dao.impl.OrderDaoImpl;
import com.gmail.vpshulgaa.service.OrderService;
import com.gmail.vpshulgaa.service.converter.impl.todto.OrderDtoConverter;
import com.gmail.vpshulgaa.service.converter.impl.toentity.OrderConverter;
import com.gmail.vpshulgaa.service.dto.OrderDto;
import com.gmail.vpshulgaa.service.util.ServiceUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderConverter orderConverter = new OrderConverter();
    private OrderDtoConverter orderDtoConverter = new OrderDtoConverter();

    @Override
    public OrderDto findOne(Long id) {
        OrderDto orderDto = null;
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Order order = orderDao.findOne(id);
            orderDto = orderDtoConverter.toDto(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to get order", e);
        }
        return orderDto;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Order order = orderConverter.toEntity(orderDto);
            orderDao.create(order);
            orderDto = orderDtoConverter.toDto(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save order", e);
        }
        return orderDto;
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Order order = orderConverter.toEntity(orderDto);
            orderDao.update(order);
            orderDto = orderDtoConverter.toDto(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update order", e);
        }
        return orderDto;
    }

    @Override
    public OrderDto delete(OrderDto orderDto) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            Order order = orderConverter.toEntity(orderDto);
            orderDao.delete(order);
            orderDto = orderDtoConverter.toDto(order);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete order", e);
        }
        return orderDto;
    }

    @Override
    public void deleteById(Long id) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            orderDao.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete order", e);
        }
    }

    @Override
    public List<OrderDto> findOrdersByUserId(Long userId) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> orders;
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = ServiceUtils.getStartedTransaction(session);
            orders = orderDao.findordersByUserId(userId);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDto(order));
            }
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find orders", e);
        }
        return ordersDto;
    }
}
