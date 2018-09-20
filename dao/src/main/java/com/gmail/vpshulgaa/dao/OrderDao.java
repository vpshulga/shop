package com.gmail.vpshulgaa.dao;

import com.gmail.vpshulgaa.dao.entities.Order;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findordersByUserId(Long userId);
}
