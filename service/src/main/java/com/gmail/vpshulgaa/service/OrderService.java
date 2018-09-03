package com.gmail.vpshulgaa.service;

import com.gmail.vpshulgaa.dao.entities.Order;
import java.util.List;

public interface OrderService {
    Order findOne(final Long id);

    List<Order> findAll();

    void create(final Order order);

    void update(final Order order);

    void delete(final Order order);

    void deleteById(final Long id);
}
