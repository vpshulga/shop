package com.gmail.vpshulgaa.dao.impl;

import com.gmail.vpshulgaa.dao.OrderDao;
import com.gmail.vpshulgaa.dao.entities.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }
}
